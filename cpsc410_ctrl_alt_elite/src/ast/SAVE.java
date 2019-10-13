package ast;

import mainrun.Main;

import java.util.ArrayList;
import java.util.List;

public class SAVE extends STATEMENT{
    String name;
    List<String> names = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("save");
        tokenizer.getAndCheckNext(":");

        if (tokenizer.checkToken("all")){
            name = tokenizer.getNext();
            names.add(name);
        }
        else {
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

    }

    @Override
    public void nameCheck() {
        for (String s: names) {
            if(!Main.variables.containsKey(s)){
                System.exit(0);
            }
        }
    }
}