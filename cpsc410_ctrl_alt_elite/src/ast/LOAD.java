package ast;

import java.io.File;
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
        System.out.println("PRINTING THE LIST OF FILES");
        for (File f: listOfFiles) {
            System.out.println(f);

        }




    }

    public String getDir(){
        return this.dir;
    }
}