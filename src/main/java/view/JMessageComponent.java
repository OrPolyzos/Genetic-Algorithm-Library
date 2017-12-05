package view;


import javax.swing.*;
import java.awt.*;

public class JMessageComponent extends JComponent {

    private final String message;
    private final double x;
    private final double y;

    public JMessageComponent(String message, double x, double y) {
        super();
        this.message = message;
        this.x = x;
        this.y = y;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setColor(Color.black);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawString(message, (float) x, (float) y);
    }
}
