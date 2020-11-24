package org.opencv.highgui;

import com.meyoustu.amuse.gous.graphics.Swing;
import com.meyoustu.amuse.gous.util.Resource;
import org.opencv.core.Mat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * This class was designed for use in Java applications
 * to recreate the OpenCV HighGui functionalities.
 */
public final class HighGui {

    // Constants for namedWindow
    public final static int WINDOW_NORMAL = ImageWindow.WINDOW_NORMAL;
    public final static int WINDOW_AUTOSIZE = ImageWindow.WINDOW_AUTOSIZE;

    // Control Variables
    public static int n_closed_windows = 0;
    public static int pressedKey = -1;
    public static CountDownLatch latch = new CountDownLatch(1);

    // Windows Map
    public static Map<String, ImageWindow> windows = new HashMap<>();

    public static void namedWindow(String winname) {
        namedWindow(winname, HighGui.WINDOW_AUTOSIZE);
    }

    public static void namedWindow(String winname, int flag) {
        ImageWindow newWin = new ImageWindow(winname, flag);
        if (windows.get(winname) == null) {
            windows.put(winname, newWin);
        }
    }


    /**
     * @param winname
     * @param imageWindow
     * @author Liangcheng Juves
     * Created at 2020/5/24 15:23
     */
    private static void customWindow(String winname, ImageWindow imageWindow) {
        if (imageWindow != null) {
            JFrame jFrame = new JFrame(winname);
            jFrame.setLocationRelativeTo(null);
            jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
                    HighGui.class.getPackageName().replace(".", "/") +
                            "/icon.png"
            ));

            imageWindow.frame = jFrame;
            imageWindow.x = (Swing.getDisplayWidth() - imageWindow.width) / 2;
        }
    }


    public static void imshow(String winname, Mat img) {
        if (img.empty()) {
            System.err.println("Error: Empty image in imshow");
            System.exit(-1);
        } else {
            // @author Liangcheng Juves
            String defWinName = "OpenCV Modified By Liangcheng Juves";
            winname = winname != null ?
                    winname.isEmpty() ?
                            defWinName :
                            winname :
                    defWinName;
            // ... end
            ImageWindow tmpWindow = windows.get(winname);

            if (tmpWindow == null) {
                ImageWindow newWin = new ImageWindow(winname, img);
                // @author Liangcheng Juves
                customWindow(winname, newWin);
                // ... end
                windows.put(winname, newWin);
            } else {
                tmpWindow.setMat(img);
            }
            // @author Liangcheng Juves
            customWindow(winname, tmpWindow);
            // ... end
        }
    }

    public static Image toBufferedImage(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;

        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);

        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);

        return image;
    }

    public static JFrame createJFrame(String title, int flag) {
        JFrame frame = new JFrame(title);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                n_closed_windows++;
                if (n_closed_windows == windows.size()) {
                    latch.countDown();
                }
            }
        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                pressedKey = e.getKeyCode();
                latch.countDown();
            }
        });

        if (flag == WINDOW_AUTOSIZE) {
            frame.setResizable(false);
        }

        return frame;
    }

    public static void waitKey() {
        waitKey(0);
    }

    public static int waitKey(int delay) {
        // Reset control values
        latch = new CountDownLatch(1);
        n_closed_windows = 0;
        pressedKey = -1;

        // If there are no windows to be shown return
        if (windows.isEmpty()) {
            System.err.println("Error: waitKey must be used after an imshow");
            System.exit(-1);
        }

        // Remove the unused windows
        Iterator<Map.Entry<String, ImageWindow>> iter = windows.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, ImageWindow> entry = iter.next();
            ImageWindow win = entry.getValue();
            if (win.alreadyUsed) {
                iter.remove();
                win.frame.dispose();
            }
        }

        // (if) Create (else) Update frame
        for (ImageWindow win : windows.values()) {

            // @author Liangcheng Juves
            customWindow(win.name, win);
            // ... end

            if (win.img != null) {

                ImageIcon icon = new ImageIcon(toBufferedImage(win.img));

                if (win.lbl == null) {
                    JFrame frame = createJFrame(win.name, win.flag);


                    JLabel lbl = new JLabel(icon);
                    win.setFrameLabelVisible(frame, lbl);
                } else {
                    win.lbl.setIcon(icon);
                }
            } else {
                System.err.println("Error: no imshow associated with" + " namedWindow: \"" +
                        win.name + "\"");
                System.exit(-1);
            }
        }

        try {
            if (delay == 0) {
                latch.await();
            } else {
                latch.await(delay, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Set all windows as already used
        for (ImageWindow win : windows.values()) {
            win.alreadyUsed = true;
        }

        return pressedKey;
    }

    public static void destroyWindow(String winname) {
        ImageWindow tmpWin = windows.get(winname);
        if (tmpWin != null) {
            windows.remove(winname);
        }
    }

    public static void destroyAllWindows() {
        windows.clear();
    }

    public static void resizeWindow(String winname, int width, int height) {
        ImageWindow tmpWin = windows.get(winname);
        if (tmpWin != null) {
            tmpWin.setNewDimension(width, height);
        }
    }

    public static void moveWindow(String winname, int x, int y) {
        ImageWindow tmpWin = windows.get(winname);
        if (tmpWin != null) {
            tmpWin.setNewPosition(x, y);
        }
    }
}
