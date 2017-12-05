package view;

import domain.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.List;

public class JPolygonComponent extends JComponent {

    private List<Point> convexHull;
    private List<Point> points;


    public JPolygonComponent(List<Point> convexHull, List<Point> points) {
        super();
        this.convexHull = convexHull;
        this.points = points;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();

          /* Enable anti-aliasing and pure stroke */
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        Path2D path = new Path2D.Double();
        path.moveTo(convexHull.get(0).getX(), convexHull.get(0).getY());
        for (int i = 1; i < convexHull.size(); ++i) {
            path.lineTo(convexHull.get(i).getX(), convexHull.get(i).getY());
        }
        path.closePath();
//        graphics2D.setColor(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(path);
        graphics2D.setColor(Color.RED);
        for (Point point : points) {
//            if (!MathUtilities.isInside(convexHull, point)){
            Ellipse2D.Double shape = new Ellipse2D.Double(point.getX() - 1.25, point.getY() - 1.25, 2.5, 2.5);
            graphics2D.fill(shape);
//            }

        }
    }
}
