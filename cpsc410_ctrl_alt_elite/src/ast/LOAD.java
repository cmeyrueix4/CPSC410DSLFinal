package ast;

import libs.NoFileFoundException;
import mainrun.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        ArrayList<String> existingFileNames = new ArrayList<>();
        for (File f: listOfFiles) {
            String name = getFileName(f);
            existingFileNames.add(name);
        }

        if (!photos.get(0).equals("all")) {
            for (String declared : photos) {
                if (!existingFileNames.contains(declared)) {
                    throw new NoFileFoundException(declared);
                }
            }
        }

//        System.out.println("PRINTING THE LIST OF FILES");
        for (File f: listOfFiles) {
//            System.out.println(f);
            String name = getFileName(f);

            if (photos.get(0).equals("all")) {
                try {
                    BufferedImage img = ImageIO.read(f);
                    Main.variables.put(name, img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {

                try {
                    BufferedImage img = ImageIO.read(f);
                    Main.variables.put(name, img);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println(Main.variables);

    }

    private String getFileName(File f) {
        String name = f.getName();
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
//            System.out.println(name);
        }
        return name;
    }

    @Override
    public void nameCheck() {
        if (photos.get(0).equals("all")) {
            File folder = new File(dir);
            File[] listOfFiles = folder.listFiles();

            ArrayList<String> existingFileNames = new ArrayList<>();
            for (File f: listOfFiles) {
                String name = getFileName(f);
                existingFileNames.add(name);
                Main.variables.put(name, "");
            }
            System.out.println("Adding all the photos in directory: " + dir);
        }
        else {
            for (String photo: photos) {
                Main.variables.put(photo, "");
            }
        }

    }

    public String getDir(){
        return this.dir;
    }
}