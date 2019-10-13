package filter;

import org.opencv.core.Mat;

public class Vignette implements Filter {
    @Override
    public Mat process(Mat inputImage) {
        return inputImage;
    }

    @Override
    public boolean setParameters(String... args) {
        return false;
    }
}
