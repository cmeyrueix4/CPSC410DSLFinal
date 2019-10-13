package filter;

import org.opencv.core.Mat;

public class Contrast implements Filter {

    @Override
    public Mat process(Mat inputImage) {
        Mat output = inputImage.clone();
        inputImage.convertTo(output, -1, 4, -100);
        return output;
    }

    @Override
    public boolean setParameters(String... args) {
        return false;
    }
}
