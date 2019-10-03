package ast;

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
            while(!tokenizer.checkToken("end")){
                name = tokenizer.getNext();
                names.add(name);
            }
            tokenizer.getAndCheckNext("end");
        }

    }

    @Override
    public void evaluate() {

    }
}