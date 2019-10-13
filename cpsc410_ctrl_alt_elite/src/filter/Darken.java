package filter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Darken implements Filter {
    @Override
    public Mat process(Mat inputImage) {
        Mat mask = new Mat(inputImage.rows(), inputImage.cols(), inputImage.type(), new Scalar(0x20, 0x20, 0x20));
        Mat outputImg = inputImage.clone();
        Core.subtract(inputImage, mask, outputImg);
        return outputImg;
    }

    @Override
    public boolean setParameters(String... args) {
        return false;
    }
}
