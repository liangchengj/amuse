package com.meyoustu.amuse.gous.graphics;

import com.meyoustu.amuse.gous.util.Constants;
import com.meyoustu.amuse.gous.util.Resource;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;

import static java.awt.Toolkit.getDefaultToolkit;


/**
 * @author Liangcheng Juves
 * Created at 2020/05/17 17:12
 */
public abstract class Swing {

    public static final String NORTH = com.meyoustu.amuse.gous.util.Constants.bytesToString(
            (byte) 'N', (byte) 'o', (byte) 'r', (byte) 't', (byte) 'h'
    );

    public static final String SOUTH = com.meyoustu.amuse.gous.util.Constants.bytesToString(
            (byte) 'S', (byte) 'o', (byte) 'u', (byte) 't', (byte) 'h'
    );

    public static final String WEST = com.meyoustu.amuse.gous.util.Constants.bytesToString(
            (byte) 'W', (byte) 'e', (byte) 's', (byte) 't'
    );

    public static final String EAST = com.meyoustu.amuse.gous.util.Constants.bytesToString(
            (byte) 'E', (byte) 'a', (byte) 's', (byte) 't'
    );

    protected Swing(String title) {
        if (jFrame == null) {
            synchronized (JFrame.class) {
                jFrame = (jFrame == null) ?
                        new JFrame() :
                        jFrame;
            }
        }
        container = jFrame.getContentPane();
        container.setBackground(Color.WHITE);
        jFrame.setSize(600, 420);
        jFrame.setTitle(title);
        jFrame.setLocationRelativeTo(null);

        if (springLayout == null) {
            synchronized (SpringLayout.class) {
                springLayout = (springLayout == null) ?
                        new SpringLayout() :
                        springLayout;
            }
        }
        jFrame.setLayout(springLayout);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowIcon("");
    }

    protected Swing() {
        this(com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) ' '
        ) + Swing.class.getSimpleName());
    }

    private JFrame jFrame;
    private Container container;
    private SpringLayout springLayout;

    protected final JFrame getWindow() {
        return jFrame;
    }

    protected final Container getContainer() {
        return container;
    }

    protected final void setWindowIcon(String pathName) {
        String info = com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) 'G', (byte) 'e', (byte) 't', (byte) ' ', (byte) 'a',
                (byte) 'n', (byte) ' ', (byte) 'e', (byte) 'm', (byte) 'p',
                (byte) 't', (byte) 'y', (byte) ' ', (byte) 'p', (byte) 'i',
                (byte) 'c', (byte) 't', (byte) 'u', (byte) 'r', (byte) 'e',
                (byte) '.'
        );
        Image image;
        try {
            image = ImageIO.read(new FileInputStream(new File(pathName)));
            if (image == null) {
                throw new NullPointerException(info);
            }
        } catch (Throwable t) {
            try {
                image = ImageIO.read(Resource.getAsStream(pathName));
                if (image == null) {
                    throw new NullPointerException(info);
                }
            } catch (Throwable tx) {
                image = getDefaultWindowIcon();
            }
        }
        jFrame.setIconImage(image);
    }

    public static final Image getDefaultWindowIcon() {
        try {
            return ImageIO.read(Swing.class.getResourceAsStream(com.meyoustu.amuse.gous.util
                    .Constants.bytesToString(
                            (byte) 'l', (byte) 'o', (byte) 'g', (byte) 'o', (byte) '.',
                            (byte) 'p', (byte) 'n', (byte) 'g'
                    )));
        } catch (Throwable txx) {
            // Never happened
            return null;
        }
    }

    protected final Image getWindowIcon() {
        return jFrame.getIconImage();
    }

    protected final void add(Component... components) {
        for (Component component : components) {
            if (component != null) {
                container.add(component);
                container.repaint();
                jFrame.setVisible(true);
            }
        }
    }

    protected final void remove(Component... components) {
        for (Component component : components) {
            if (component != null) {
                container.remove(component);
                container.repaint();
                jFrame.setVisible(true);
            }
        }
    }

    protected final void putConstraint(String e1, Component c1, int pad,
                                       String e2, Component c2) {
        springLayout.putConstraint(e1, c1, pad, e2, c2);
    }

    protected final void putCenterHorizontal(Component... components) {
        for (Component component : components) {
            if (component != null) {
                int pad = (jFrame.getWidth() - component.getWidth()) / 6;
                putConstraint(EAST, component, -pad, EAST, container);
                putConstraint(WEST, component, pad, WEST, container);
            }
        }
    }

    protected final void putCenterVertical(Component... components) {
        for (Component component : components) {
            if (component != null) {
                int pad = (jFrame.getHeight() - component.getHeight()) / 6;
                putConstraint(SOUTH, component, -pad, SOUTH, container);
                putConstraint(NORTH, component, pad, NORTH, container);
            }
        }
    }

    protected final void putCenter(Component... components) {
        putCenterVertical(components);
        putCenterHorizontal(components);
    }

    protected final void setComponentsFont(Font font, Component... components) {
        for (Component component : components) {
            if (component != null) {
                component.setFont(font);
            }
        }
    }

    protected void show() {
        jFrame.setVisible(true);
    }

    public static final int getDisplayWidth() {
        return getDefaultToolkit().getScreenSize().width;
    }

    public static final Color getGrayColor(int grayValue) {
        return new Color(grayValue, grayValue, grayValue);
    }

    public static final int getDisplayHeight() {
        return getDefaultToolkit().getScreenSize().height;
    }


    public static void main(String[] args) {
//        Swing swing = new Swing() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//        };
//        swing.show();

        showCodePanel();
    }


    public static final void showCodePanel() {
        Swing swing = new Swing(com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) ' ', (byte) 'C', (byte) 'o', (byte) 'd', (byte) 'e',
                (byte) ' ', (byte) 'P', (byte) 'a', (byte) 'n', (byte) 'e',
                (byte) 'l'
        )) {
            @Override
            protected void show() {
                super.show();
            }
        };


        JLabel jLabel = new JLabel(com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) '<', (byte) 'h', (byte) 't', (byte) 'm', (byte) 'l',
                (byte) '>', (byte) '<', (byte) 'b', (byte) 'o', (byte) 'd',
                (byte) 'y', (byte) '>', (byte) 'P', (byte) 'l', (byte) 'e',
                (byte) 'a', (byte) 's', (byte) 'e', (byte) ' ', (byte) 'e',
                (byte) 'n', (byte) 't', (byte) 'e', (byte) 'r', (byte) ' ',
                (byte) 't', (byte) 'h', (byte) 'e', (byte) ' ', (byte) 't',
                (byte) 'e', (byte) 'x', (byte) 't', (byte) ' ', (byte) 'y',
                (byte) 'o', (byte) 'u', (byte) ' ', (byte) 'w', (byte) 'a',
                (byte) 'n', (byte) 't', (byte) ' ', (byte) 't', (byte) 'o',
                (byte) ' ', (byte) 'u', (byte) 's', (byte) 'e', (byte) ' ',
                (byte) 'a', (byte) 'g', (byte) 'a', (byte) 'i', (byte) 'n',
                (byte) 's', (byte) 't', (byte) ' ', (byte) 'd', (byte) 'e',
                (byte) 'c', (byte) 'o', (byte) 'm', (byte) 'p', (byte) 'i',
                (byte) 'l', (byte) 'a', (byte) 't', (byte) 'i', (byte) 'o',
                (byte) 'n', (byte) ':', (byte) '<', (byte) 'b', (byte) 'o',
                (byte) 'd', (byte) 'y', (byte) '/', (byte) '>', (byte) '<',
                (byte) 'h', (byte) 't', (byte) 'm', (byte) 'l', (byte) '/',
                (byte) '>'
        ));

        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea jTextArea = new JTextArea(7, 30);
        jTextArea.setBackground(getGrayColor(248));
        jTextArea.setLineWrap(true);
        JButton jButton = new JButton(com.meyoustu.amuse.gous.util.Constants.bytesToString(
                (byte) 'B', (byte) 'e', (byte) 'g', (byte) 'i', (byte) 'n'
        ));
        jButton.setBackground(getGrayColor(240));
        jButton.setBorderPainted(false);


        swing.putConstraint(NORTH, jLabel, 20, NORTH, swing.container);
        swing.putConstraint(NORTH, jTextArea, 30, SOUTH, jLabel);
        swing.putConstraint(SOUTH, jTextArea, 0, NORTH, jButton);
        swing.putConstraint(SOUTH, jButton, -40, SOUTH, swing.container);

        swing.putCenterHorizontal(jLabel, jTextArea, jButton);
        swing.setComponentsFont(
                new Font(Constants.bytesToString(), Font.BOLD | Font.ITALIC, 22),
                jLabel, jTextArea
        );
        swing.add(jLabel, jTextArea, jButton);

        jButton.addActionListener(e -> {
            String content = jTextArea.getText();
            if (content != null) {
                swing.remove(jButton, jTextArea, jLabel);

                JTextArea area = new JTextArea(7, 30);
                area.setText(Constants.againstDecompile(content));
                area.setLineWrap(true);

                swing.putCenter(area);
                swing.add(area);
            }

        });

        swing.show();

    }

}
