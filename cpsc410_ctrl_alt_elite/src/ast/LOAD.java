package ast;

import mainrun.Main;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LOAD extends STATEMENT{
    String dir; 
    List<String> photos = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("from");
        dir = tokenizer.getNext();

        tokenizer.getAndCheckNext(":");

        if (tokenizer.checkToken("all")){
            String all = tokenizer.getNext();
            photos.add(all);
        }else {
            String first = tokenizer.getNext();
            photos.add(first);
            System.out.println(first);
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                String photo = tokenizer.getNext();
                photos.add(photo);
            }
        }
        System.out.println(photos);
    }

    @Override
    public void evaluate() {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        System.out.println("PRINTING THE LIST OF FILES");
        for (File f: listOfFiles) {
//            System.out.println(f.getAbsoluteFile());
            System.out.println(f);

            String name = f.getName();
            int pos = name.lastIndexOf(".");
            if (pos > 0) {
                name = name.substring(0, pos);
                System.out.println(name);
            }

            try {
                BufferedImage img = ImageIO.read(f);
                Main.variables.put(name,img);
//                System.out.println(img.getWidth());
//                Main.variables.put()
//                ImageIcon icon=new ImageIcon(img);
//                JFrame frame=new JFrame();
//                frame.setLayout(new FlowLayout());
//                frame.setSize(500,900);
//                JLabel lbl=new JLabel();
//                lbl.setIcon(icon);
//                frame.add(lbl);
//                frame.setVisible(true);
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Main.variables);

    }

    @Override
    public void nameCheck() {
        for (String photo: photos) {
            photo = dir + "/" + photo + ".jpg";
            Main.variables.put(photo, "");
        }
    }

    public String getDir(){
        return this.dir;
    }
}