package view;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JFrame {
    private int width;
    private int height;

    public Canvas(int width, int height) {
        super("New ConvexHull Example");
        this.width = width;
        this.height = height;
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
