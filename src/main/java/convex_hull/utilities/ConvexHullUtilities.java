package convex_hull.utilities;

import convex_hull.domain.Point;
import view.Canvas;

import java.util.*;

import static convex_hull.utilities.MathUtilities.isInside;

public class ConvexHullUtilities {

    public static List<Point> calculateSickJoints(List<Point> convexHull) {
        int winding = MathUtilities.calculateWinding(convexHull);
        List<Point> sickJoints = new ArrayList<>();
        int sickNumber;
        //Means convexHull is clock wise
        if (winding == 1) {
            sickNumber = 1;
        } else {
            sickNumber = 2;
        }
//        sickNumber = 2;
        for (int i = 0; i < convexHull.size(); i++) {
            Point p0, p1, p2;
            if (i < convexHull.size() - 2) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(i + 1);
                p2 = convexHull.get(i + 2);
                int sds = MathUtilities.orientation(p0, p1, p2);

                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            } else if (i == convexHull.size() - 2) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(i + 1);
                p2 = convexHull.get(0);
                int sds = MathUtilities.orientation(p0, p1, p2);
                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            } else if (i == convexHull.size() - 1) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(0);
                p2 = convexHull.get(1);
                int sds = MathUtilities.orientation(p0, p1, p2);

                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            }
        }
        return sickJoints;
    }

    public static List<Point> calculateOutsidePoints(List<Point> convexHull, List<Point> allPoints) {
        List<Point> outsidePoints = new ArrayList<>();
        for (Point point : allPoints) {
            if (!MathUtilities.isInside(convexHull, point) && !convexHull.contains(point)) {
                outsidePoints.add(new Point(point));
            }
        }
        return outsidePoints;
    }

    public static int calculateIntersections(List<Point> convexHull) {
        int intersections = 0;
        for (int i = 0; i < convexHull.size() - 1; i++) {
            int currentEdgeStart = i;
            int currentEdgeEnd = i + 1;
            for (int j = currentEdgeEnd + 1; j < convexHull.size() - 1; j++) {
                int testEdgeStart = j;
                int testEdgeEnd = j + 1;

                if (MathUtilities.doIntersect(convexHull.get(currentEdgeStart), convexHull.get(currentEdgeEnd),
                        convexHull.get(testEdgeStart), convexHull.get(testEdgeEnd))) {
                    intersections++;
                }
            }
        }
        int currentEdgeStart = convexHull.size() - 1;
        int currentEdgeEnd = 0;
        for (int i = 1; i < convexHull.size() - 2; i++) {
            int testEdgeStart = i;
            int testEdgeEnd = i + 1;
            if (MathUtilities.doIntersect(convexHull.get(currentEdgeStart), convexHull.get(currentEdgeEnd),
                    convexHull.get(testEdgeStart), convexHull.get(testEdgeEnd))) {
                intersections++;
            }
        }
        return intersections;
    }

    public static int findClosest(Point checkPoint, List<Point> polygon) {
        double minDistance = Integer.MAX_VALUE;
        int minPoint = 0;
        for (int i = 0; i < polygon.size(); i++) {
            double d = MathUtilities.calculateDistance(checkPoint, polygon.get(i));
            if (d < minDistance && d > 0) {
                minDistance = d;
                minPoint = i;
            }
        }
        return minPoint;
    }

    public static Set<Point> calculateEdgePolygon(List<Point> points) {
        double edgeLeft = Integer.MAX_VALUE;
        Point edgeLeftPoint = null;

        double edgeRight = Integer.MIN_VALUE;
        Point edgeRightPoint = null;

        double edgeTop = Integer.MAX_VALUE;
        Point edgeTopPoint = null;

        double edgeBottom = Integer.MIN_VALUE;
        Point edgeBottomPoint = null;

        for (Point point : points) {
            //Getting the 4 edges
            if (Double.compare(point.getX(), edgeLeft) < 0) {
                edgeLeft = point.getX();
                edgeLeftPoint = point;
            }
            if (Double.compare(point.getX(), edgeRight) > 0) {
                edgeRight = point.getX();
                edgeRightPoint = point;
            }
            if (Double.compare(point.getY(), edgeTop) < 0) {
                edgeTop = point.getY();
                edgeTopPoint = point;
            }
            if (Double.compare(point.getY(), edgeBottom) > 0) {
                edgeBottom = point.getY();
                edgeBottomPoint = point;
            }
        }
        Set<Point> edgePolygon = new LinkedHashSet<>();
        edgePolygon.add(new Point(edgeBottomPoint));
        edgePolygon.add(new Point(edgeLeftPoint));
        edgePolygon.add(new Point(edgeTopPoint));
        edgePolygon.add(new Point(edgeRightPoint));

        return edgePolygon;
    }

    public static List<Point> calculatePossibleHullPoints(Set<Point> edgePolygon, List<Point> points) {
        Set<Point> possibleHullPoints = new LinkedHashSet<>();
        for (Point point : points) {
            if (!isInside(new ArrayList<>(edgePolygon), point)) {
                possibleHullPoints.add(new Point(point));
            }
        }
        return new ArrayList<>(possibleHullPoints);
    }

    public static List<Point> generatePoints(int pointsCount, Canvas canvas) {
        List<Point> pointList = new ArrayList<>();
//        pointList.add(new Point(0,45,45));
//        pointList.add(new Point(1,150,45));
//        pointList.add(new Point(2,45,355));
//        pointList.add(new Point(3,150,355));
//        pointList.add(new Point(4,65,234));
//        pointList.add(new Point(5,186,434));
//        pointList.add(new Point(6,195,255));
//        pointList.add(new Point(7,250,300));
        //Creating random coordinates (points)
        int border = 40;
        for (int i = 0; i < pointsCount; i++) {
            double x = new Random().nextDouble() * canvas.getWidth();
            if (x < border) {
                x += border;
            } else if (x >= canvas.getWidth() - border) {
                x -= border;
            }
            double y = new Random().nextDouble() * canvas.getHeight();
            if (y < border) {
                y += border;
            } else if (y >= canvas.getHeight() - border) {
                y -= border;
            }
            Point point = new Point(i, x, y);
            pointList.add(point);
        }
        return pointList;
    }

}
