package org.opencv.highgui;

import org.junit.jupiter.api.Test;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.osgi.OpenCVNativeLoader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/24 14:45
 */
class HighGuiTest extends OpenCVNativeLoader {

    @Test
    void imshow() {
        init();

        HighGui.imshow("",
                Imgcodecs.imread("C:\\Users\\LiangchengJ\\OneDrive\\imgs\\logo.png"));
        HighGui.waitKey();
    }

    public static void main(String[] args) {
        new HighGuiTest().init();

        HighGui.imshow("",
                Imgcodecs.imread("C:\\Users\\LiangchengJ\\OneDrive\\imgs\\logo.png"));
        HighGui.waitKey();
    }
}