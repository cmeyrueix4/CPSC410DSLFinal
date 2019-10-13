package filter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Invert implements Filter {
    @Override
    public Mat process(Mat inputImage) {
        Mat mask = new Mat(inputImage.rows(), inputImage.cols(), inputImage.type(), new Scalar(0xFF, 0xFF, 0xFF));
        Mat outputImg = inputImage.clone();
        Core.subtract(mask, inputImage, outputImg);
        return outputImg;
    }

    @Override
    public boolean setParameters(String... args) {
        return false;
    }
}
