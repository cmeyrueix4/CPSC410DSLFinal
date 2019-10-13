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
        for (String n: names) {
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
                    ImageIO.write(img, "jpg", new File("output_" + n));
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
        List<BufferedImage> photos = null;
        try {
            //noinspection unchecked
            photos = (List<BufferedImage>) Main.variables.get(listName);
            /* Running a for loop through all the photos we'd like to make a gif of. The steps to do this
             *  are the same as the steps done to work with the first photo of the gif.
             */
        } catch (ClassCastException ignored) {
            System.err.println("Tried to create a gif from a not gif variable!");
        }


        // Setting the name of the output file that will become the gif
        ImageOutputStream output = null;

        output = new FileImageOutputStream(new File
                ("C:\\Users\\HP USER\\Desktop\\CPSC410DSL\\cpsc410_ctrl_alt_elite\\" + name));


        // creating the actual GifCreator object that will make the gif
        GifCreator writer = new GifCreator(output,
                photos.get(0).getType(),
                400,
                true);

        for (BufferedImage photo : photos) {
            writer.writeToSequence(photo);
        }

        writer.close();
        output.close();
    }
}