package mainrun;

import ast.PROGRAM;
import libs.Node;
import libs.Tokenizer;
import org.opencv.core.Core;

import java.util.Arrays;
import java.util.List;

public class Main {

    static {
        // Loads the OpenCV Library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
//        Main.class.getResource("input.txt");
        List<String> literals = Arrays.asList("load", ",", "save", "create", "filter", "all", "collage", "from", ":", "gif");
        Tokenizer.makeTokenizer("input.txt",literals);
//        Tokenizer.makeTokenizer(String.valueOf(Main.class.getResource("../../input.txt")), literals);
//        Tokenizer.makeTokenizer("/Users/ceylin/Documents/UBC/CPSC/410/CPSC410DSL/cpsc410_ctrl_alt_elite/input.txt",literals);
//        Tokenizer.makeTokenizer("C:\\Users\\Cyrielle\\IdeaProjects\\CPSC410DSLV2\\cpsc410_ctrl_alt_elite\\input.txt",literals);
//        Node.setWriter("C:\\Users\\HP USER\\Desktop\\CPSC410DSL\\cpsc410_ctrl_alt_elite\\gifOutput");
        Node program = new PROGRAM();
        program.parse();
        program.evaluate();

    }

}
