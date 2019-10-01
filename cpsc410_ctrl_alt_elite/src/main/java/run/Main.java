package run;

import org.opencv.core.Core;

public class Main {

    static {
        // Loads the OpenCV Library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

}
