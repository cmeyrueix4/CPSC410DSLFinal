package ast;

//import filter.Blur;
//import filter.Filter;
//import filter.Sharpen;
//import filter.Vignette;
//import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

public class FILTER extends STATEMENT {
//
//    private enum ImageFilters {
//        BLUR("blur", new Blur()),
//        SHARPEN("sharpen", new Sharpen()),
//        VIGNETTE("vignette", new Vignette());
//
//        private final String tokenName;
//        private final Filter filter;
//
//        ImageFilters(String tokenName, Filter filter) {
//            this.tokenName = tokenName;
//            this.filter = filter;
//        }
//
//    }

    String filterOption;
    List<String> photos = new ArrayList<>();

    @Override
    public void parse() {
        String photo;

        tokenizer.getAndCheckNext("filter");
        filterOption = tokenizer.getNext();

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
//        for (ImageFilters f : ImageFilters.values()) {
//            if (filterOption.equals(f.tokenName)) {
//                Mat ret = f.filter.process(null); // TODO
//            }
//        }
    }
}