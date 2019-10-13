package filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


public class FilterTest {

    private static final byte[] ZEROS = new byte[16];
    private static final byte[] SMALL_NOISE = new byte[]{
            0, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 0, 1,
            0, 0, 0, 0,
    };

    @Before
    public void setUp() throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void testBlur() {
        Filter b = new Blur();

        Mat input = new Mat(4, 4, CvType.CV_8U);
        input.put(0, 0, SMALL_NOISE);

        Mat output = b.process(input);

        Assert.assertNotEquals(output, input);

        // Compare pixel values
        byte[] pxOutput = new byte[16];
        output.get(0, 0, pxOutput);
        Assert.assertArrayEquals(ZEROS, pxOutput);
    }

    @Test
    public void testSharp() {
        Filter s = new Sharpen();

        Mat input = new Mat(4, 4, CvType.CV_8U);
        input.put(0, 0, SMALL_NOISE);

        Mat output = s.process(input);

        Assert.assertNotEquals(output, input);

        // Compare pixel values
        byte[] pxOutput = new byte[16];
        output.get(0, 0, pxOutput);
        byte[] expected = new byte[]{
                0, 0, 0, 0,
                0, 2, 0, 0,
                0, 0, 0, 2,
                0, 0, 0, 0};
        Assert.assertArrayEquals(expected, pxOutput);


    }
    @Test
    public void manualTest() {
        String path = "C:\\Users\\BenH\\Documents\\_ubc\\CPSC410\\";
        Mat img = Imgcodecs.imread(path + "img.jpg");

        Mat blur = new Blur().process(img);
        Imgcodecs.imwrite(path + "blurred.jpg", blur);

        Mat sharp = new Sharpen().process(img);
        Imgcodecs.imwrite(path + "sharp.jpg", sharp);

        Mat invert = new Invert().process(img);
        Imgcodecs.imwrite(path + "invert.jpg", invert);

        Mat bright = new Brighten().process(img);
        Imgcodecs.imwrite(path + "bright.jpg", bright);

        Mat dark = new Darken().process(img);
        Imgcodecs.imwrite(path + "darken.jpg", dark);

        Mat sat = new Contrast().process(img);
        Imgcodecs.imwrite(path + "saturate.jpg", sat);
    }
}