package ast;

import mainrun.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class COLLAGE extends STATEMENT{
    String name;
    ArrayList<String> photos = new ArrayList<>();
    LOAD loadObject;
    List<BufferedImage> imagesToMakeCollageWith = new ArrayList<>();

    public COLLAGE(STATEMENT load){
        this.loadObject = (LOAD) load;
    }


    @Override
    public void parse() {
        tokenizer.getAndCheckNext("collage");
        tokenizer.getAndCheckNext("create");
        name = tokenizer.getNext();

        tokenizer.getAndCheckNext(":");

        if (tokenizer.checkToken("all")) {
            String all = tokenizer.getNext();
            photos.add(all);
        }
        else {
            String first = tokenizer.getNext();
            photos.add(first);
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                String photo = tokenizer.getNext();
                photos.add(photo);
            }
        }

    }

    @Override
    public void evaluate() {
        for (int k = 0; k < photos.size(); k++) {
                BufferedImage nextImage = (BufferedImage)variables.get(photos.get(k));
                imagesToMakeCollageWith.add(nextImage);
    }
}

    public List<BufferedImage> getImagesToMakeCollageWith{
        return this.imagesToMakeCollageWith;
    }

    @Override
    public void nameCheck() {
        for (String s: photos) {
            if(!Main.variables.containsKey(s)){
                System.exit(0);
            }
        }

        Main.variables.put(name, "");

    }

  
}