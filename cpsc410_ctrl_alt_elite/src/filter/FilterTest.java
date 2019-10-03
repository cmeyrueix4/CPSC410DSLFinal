package filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

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

        Mat expected = new Mat(4, 4, CvType.CV_8U);
        expected.put(0, 0, ZEROS);

        Mat output = b.process(input);

        Assert.assertNotEquals(output, input);

        // Compare pixel values
        byte[] pxOutput = new byte[16];
        output.get(0, 0, pxOutput);
        Assert.assertArrayEquals(ZEROS, pxOutput);
    }
}