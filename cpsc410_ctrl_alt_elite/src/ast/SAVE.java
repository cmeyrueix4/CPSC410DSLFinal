package ast;

import GifsCollages.GifCreator;
import mainrun.Main;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAVE extends STATEMENT {
    public static final int TOTAL_GIF_LENGTH = 2000;
    String name;
    List<String> names = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("save");
        tokenizer.getAndCheckNext(":");

        if (tokenizer.checkToken("all")) {
            name = tokenizer.getNext();
            names.add(name);
        } else {
            String first = tokenizer.getNext();
            names.add(first);
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                String photo = tokenizer.getNext();
                names.add(photo);
            }
        }

    }

    @Override
    public void evaluate() {
        for (String n : names) {
            System.out.println(String.format("Trying to save %s", n));
            if (Main.variables.get(n) instanceof List) {
                // Create a gif
                try {
                    saveGif(n);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                BufferedImage img = (BufferedImage) Main.variables.get(n);
                try {
                    File target = new File("output_" + n);
                    ImageIO.write(img, "jpg", new File("output_" + n));
                    System.out.println(String.format("Image saved to %s", target.getAbsolutePath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void nameCheck() {
        for (String s : names) {
            if (!Main.variables.containsKey(s)) {
                System.exit(0);
            }
        }
    }

    private void saveGif(String listName) throws IOException {
        System.out.println("Creating GIF");
        List<BufferedImage> photos = null;
        try {
            //noinspection unchecked
            photos = (List<BufferedImage>) Main.variables.get(listName);
        } catch (ClassCastException ignored) {
            System.err.println("Tried to create a gif from a not gif variable!");
        }

        ImageOutputStream output = new FileImageOutputStream(new File(listName + ".gif"));

        GifCreator writer = new GifCreator(output,
                photos.get(0).getType(),
                TOTAL_GIF_LENGTH / photos.size(),
                true);

        for (BufferedImage photo : photos) {
            writer.writeToSequence(photo);
        }
        writer.close();
        output.close();
    }
}