package ast;

import java.util.ArrayList;
import java.util.List;

public class COLLAGE extends STATEMENT{
    String name;
    List<String> photos = new ArrayList<>();

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
            while (!tokenizer.checkToken("end")) {
                String photo = tokenizer.getNext();
                photos.add(photo);
            }
            tokenizer.getAndCheckNext("end");
        }

    }

    @Override
    public void evaluate() {

    }
}