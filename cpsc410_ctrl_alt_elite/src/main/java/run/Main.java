package run;

import org.opencv.core.Core;

public class Main {

    static {
        // Loads the OpenCV Library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        List<String> literals = Arrays.asList("from");
        Tokenizer.makeTokenizer("input.tdot",literals);
        Node program = new PROGRAM();
        program.parse();
    }

}
