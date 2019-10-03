package mainrun;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class OpenCvTest {

    @Before
    public void loadOpenCv() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    @Test
    public void testOpenCvInstalled() {
        System.out.println(String.format("OpenCV Version: %s", Core.VERSION));
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        Assert.assertNotNull(mat);
    }

}