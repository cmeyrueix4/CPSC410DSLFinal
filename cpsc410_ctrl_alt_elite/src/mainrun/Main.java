package mainrun;

import ast.PROGRAM;
import libs.Node;
import libs.Tokenizer;
import org.opencv.core.Core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static Map<String, Object> variables = new HashMap<>();

    static {
        // Loads the OpenCV Library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        List<String> literals = Arrays.asList("load", ",", "save", "create", "filter", "all", "collage", "from", ":", "gif");
        Tokenizer.makeTokenizer("input.txt",literals);
        Node program = new PROGRAM();
        program.parse();
        program.nameCheck();
        program.evaluate();

    }

}
