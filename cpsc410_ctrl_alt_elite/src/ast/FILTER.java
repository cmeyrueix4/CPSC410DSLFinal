package ast;

import filter.*;
import libs.FilterNotFoundException;
import libs.NameCheckException;
import mainrun.Main;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FILTER extends STATEMENT {

    private enum ImageFilters {
        BLUR("blur", new Blur()),
        SHARPEN("sharpen", new Sharpen()),
        CONTRAST("contrast", new Contrast()),
        DARKEN("darken", new Darken()),
        BRIGHTEN("brighten", new Brighten()),
        INVERT("invert", new Invert());

        private final String tokenName;
        private final Filter filter;

        ImageFilters(String tokenName, Filter filter) {
            this.tokenName = tokenName;
            this.filter = filter;
        }


        @Override
        public String toString() {
            return "filter:" + tokenName;
        }
    }

    String filterOption;
    List<String> photos = new ArrayList<>();

    @Override
    public void parse() {
        String photo;

        tokenizer.getAndCheckNext("filter");
        filterOption = tokenizer.getNext().toLowerCase();

        tokenizer.getAndCheckNext(":");
        if (tokenizer.checkToken("all")) {
            photo = tokenizer.getNext();
            photos.add(photo);
        } else {
            String first = tokenizer.getNext();
            photos.add(first);
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                photo = tokenizer.getNext();
                photos.add(photo);
            }
        }
    }

    @Override
    public void evaluate() {
        for (String photoName : photos) {

            BufferedImage bi = null;
            try {
                bi = (BufferedImage) Main.variables.get(photoName);
            } catch (ClassCastException ex) {
                System.err.println("GIFs can not be filtered!");
            }


            boolean found = false;

            for (ImageFilters f : ImageFilters.values()) {
                if (filterOption.equals(f.tokenName)) {
                    assert bi != null;
                    // Convert from bufferedimage to mat
                    Mat img = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
                    byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
                    img.put(0, 0, data);

                    // Run the filter
                    Mat ret = f.filter.process(img);

                    // Convert back to bufferedImage
                    BufferedImage output = null;
                    MatOfByte mob = new MatOfByte();
                    Imgcodecs.imencode(".jpg", ret, mob);
                    try {
                        output = ImageIO.read(new ByteArrayInputStream(mob.toArray()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Main.variables.put(photoName, output);

                    found = true;
                    break;
                }
            }

            if (!found) {
                throw new FilterNotFoundException("Available filters are: " + Arrays.toString(ImageFilters.values()));
            }
        }
    }

    @Override
    public void nameCheck() {
        for (String s : photos) {
            if (!Main.variables.containsKey(s)) {
                throw new NameCheckException(s);
            }
        }
    }

}