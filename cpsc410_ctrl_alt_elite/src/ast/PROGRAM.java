package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends Node {
    private List<STATEMENT> statements = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("load");
        STATEMENT loadS = new LOAD();
        loadS.parse();
        statements.add(loadS);
        while(!tokenizer.checkToken("save")){
            STATEMENT s = null;
            if (tokenizer.checkToken("filter")){
                s = new FILTER();
            }
            else if (tokenizer.checkToken("gif")){
                s = new GIF();
            }
            else if (tokenizer.checkToken("collage")){
                s = new COLLAGE();
            }
            s.parse();
            statements.add(s);
        }
        STATEMENT saveS = new SAVE();
        saveS.parse();
        statements.add(saveS);
    }

    @Override
    public void evaluate() {

    }


}