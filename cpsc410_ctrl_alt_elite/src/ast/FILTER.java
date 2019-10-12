package ast;

import filter.*;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

public class FILTER extends STATEMENT {

    private enum ImageFilters {
        BLUR("blur", new Blur()),
        SHARPEN("sharpen", new Sharpen()),
        CONTRAST("contrast", new Contrast()),
        DARKEN("darken", new Darken()),
        BRIGHTEN("brighten", new Brighten()),
        INVERT("invert", new Invert());

        private final String tokenName;
        private final Filter filter;

        ImageFilters(String tokenName, Filter filter) {
            this.tokenName = tokenName;
            this.filter = filter;
        }

    }

    String filterOption;
    List<String> photos = new ArrayList<>();

    @Override
    public void parse() {
        String photo;

        tokenizer.getAndCheckNext("filter");
        filterOption = tokenizer.getNext().toLowerCase();

        tokenizer.getAndCheckNext(":");
        if (tokenizer.checkToken("all")) {
            photo = tokenizer.getNext();
            photos.add(photo);
        }
        else {
            String first = tokenizer.getNext();
            photos.add(first);
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                photo = tokenizer.getNext();
                photos.add(photo);
            }
        }


    }

    @Override
    public void evaluate() {
        for (ImageFilters f : ImageFilters.values()) {
            if (filterOption.equals(f.tokenName)) {
                Mat ret = f.filter.process(null); // TODO
            }
        }
    }
}