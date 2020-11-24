package com.meyoustu.amuse.gous.graphics;

import com.meyoustu.amuse.gous.util.HexConvert;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/18 22:11
 */
class SwingTest implements HexConvert {

    @Test
    void getWindow() {
        JFrame jFrame = new JFrame("XXX");
        jFrame.getContentPane().setBackground(parseColorHex("#CC5BEB"));
        jFrame.setSize(500, 300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    @Test
    void showCodePanel() {

    }

    public static void main(String[] args) {
        Swing.showCodePanel();
    }
}