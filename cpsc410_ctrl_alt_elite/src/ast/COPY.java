package ast;

import mainrun.Main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class COPY extends STATEMENT {
    private String orgPhoto;
    private List<String> copies = new ArrayList<>();


    @Override
    public void parse() {
        tokenizer.getAndCheckNext("copy");
        orgPhoto = tokenizer.getNext();
        tokenizer.getAndCheckNext("as");
        tokenizer.getAndCheckNext(":");

        String first = tokenizer.getNext();
        copies.add(first);
        while (tokenizer.checkToken(",")) {
            tokenizer.getAndCheckNext(",");
            String photo = tokenizer.getNext();
            copies.add(photo);
        }
    }

    @Override
    public void evaluate() {
        BufferedImage photoCopy = (BufferedImage) Main.variables.get(orgPhoto);
        for (String s: copies) {
            Main.variables.put(s, photoCopy);
        }

    }

    @Override
    public void nameCheck() {
        for (String s: copies) {
            Main.variables.put(s, "");
        }
    }
}