package language.filter;

import org.opencv.core.Mat;

public interface Filter {
    Mat process(Mat inputImage);
}
