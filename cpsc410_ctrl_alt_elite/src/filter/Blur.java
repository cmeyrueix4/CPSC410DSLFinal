package filter;

import org.opencv.core.Mat;

public class Blur implements Filter {
    @Override
    public Mat process(Mat inputImage) {
        return inputImage;
    }
}
