package ast;

//GIF::- "gif" "create" NAME ":" PHOTO+

import java.util.ArrayList;
import java.util.List;

public class GIF extends STATEMENT{
    String name;
    List<String> photos = new ArrayList<>();


    @Override
    public void parse() {
        tokenizer.getAndCheckNext("gif");
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

    }
}