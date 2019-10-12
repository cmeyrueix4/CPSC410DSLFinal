//package filter;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;
//
//public class Sharpen implements Filter {
//
//    @Override
//    public Mat process(Mat inputImage) {
//        Mat outputImage = inputImage.clone();
//
//        // Unsharp masking
//        // TODO parameterise
//        Imgproc.GaussianBlur(inputImage, outputImage, new Size(0, 0), 10);
//        Core.addWeighted(inputImage, 1.5, outputImage, -0.5, 0, outputImage);
//        return outputImage;
//    }
//
//    @Override
//    public boolean setParameters(String... args) {
//        return false;
//    }
//}
