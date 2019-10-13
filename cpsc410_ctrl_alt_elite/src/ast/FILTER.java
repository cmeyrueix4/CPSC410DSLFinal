package ast;

import filter.Blur;
import filter.Filter;
import filter.Sharpen;
import filter.Vignette;
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
import java.util.List;

public class FILTER extends STATEMENT {

    private enum ImageFilters {
        BLUR("blur", new Blur()),
        SHARPEN("sharpen", new Sharpen()),
        VIGNETTE("vignette", new Vignette());

        private final String tokenName;
        private final Filter filter;

        ImageFilters(String tokenName, Filter filter) {
            this.tokenName = tokenName;
            this.filter = filter;
        }

    }

    String filterOption;
    List<String> photos = new ArrayList<>();

    @Override
    public void parse() {
        String photo;

        tokenizer.getAndCheckNext("filter");
        filterOption = tokenizer.getNext();

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
        for (ImageFilters f : ImageFilters.values()) {
            if (filterOption.equals(f.tokenName)) {
                Mat ret = f.filter.process(null); // TODO

                // Convert from bufferedimage to mat
                BufferedImage bi = null; // TODO
                Mat img = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
                byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
                img.put(0, 0, data);

                // Convert back to bufferedImage
                BufferedImage output = null;
                MatOfByte mob = new MatOfByte();
                Imgcodecs.imencode(".jpg", ret, mob);
                try {
                    output = ImageIO.read(new ByteArrayInputStream(mob.toArray()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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