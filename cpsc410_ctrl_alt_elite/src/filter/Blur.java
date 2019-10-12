//package filter;
//
//import org.opencv.core.Mat;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;
//
//public class Blur implements Filter {
//
//    private Size kernelSize;
//    private double sigmaX;
//
//    public Blur() {
//        kernelSize = new Size(3, 3);
//        sigmaX = 1;
//    }
//
//    /**
//     * Overload for constructor which just calls set params as well as super
//     */
//    public Blur(String... args) {
//        super();
//        setParameters(args);
//    }
//
//    @Override
//    public Mat process(Mat inputImage) {
//        Mat outputImage = inputImage.clone();
//        Imgproc.GaussianBlur(inputImage, outputImage, kernelSize, sigmaX);
//        return outputImage;
//    }
//
//    @Override
//    public boolean setParameters(String... args) {
//        return false;
//    }
//}
