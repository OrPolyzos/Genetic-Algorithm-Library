package com.unipi.informatics.convex_hull.utilities;

import com.unipi.informatics.convex_hull.domain.Point;

import java.util.*;

import static com.unipi.informatics.convex_hull.utilities.MathUtilities.isInside;

public class CH_Utilities {

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
        for (int i = 0; i < convexHull.size(); i++) {
            Point p0, p1, p2;
            if (i < convexHull.size() - 2) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(i + 1);
                p2 = convexHull.get(i + 2);
                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            } else if (i == convexHull.size() - 2) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(i + 1);
                p2 = convexHull.get(0);
                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            } else if (i == convexHull.size() - 1) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(0);
                p2 = convexHull.get(1);
                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            }
        }
        return sickJoints;
    }

    public static List<Point> calculateOutsidePoints(List<Point> convexHull, List<Point> points) {
        List<Point> outsidePoints = new ArrayList<>();
        for (Point point : points) {
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

    public static List<Point> calculatePossibleHullPoints(List<Point> edgePolygon, List<Point> points) {
        Set<Point> possibleHullPoints = new LinkedHashSet<>();
        for (Point point : points) {
            if (!isInside(edgePolygon, point)) {
                possibleHullPoints.add(new Point(point));
            }
        }
        return new ArrayList<>(possibleHullPoints);
    }

    public static List<Point> generatePoints(int width, int height, int pointsCount) {
        Set<Point> pointSet = new HashSet<>();

        double v_border_bottom = 0.05 * height;
        double v_border_top = 0.1 * height;
        double h_border = 0.01 * width;

        for (int i = 0; i < pointsCount; i++) {
            double x = new Random().nextDouble() * (double) width;
            if (x < h_border) {
                x += h_border;
            } else if (x >= width - h_border) {
                x -= h_border;
            }
            double y = new Random().nextDouble() * (double) height;
            if (y < v_border_top) {
                y += v_border_top;
            } else if (y >= height - v_border_bottom) {
                y -= v_border_bottom;
            }
            Point point = new Point(i, x, y);
            pointSet.add(point);
        }
//        Point middle = new Point(-1, (double) width / 2, (double) 2 * height / 5);
//        int i = 0;
//        int radius;
//        if (width < height) {
//            radius = width;
//        } else {
//            radius = height;
//        }
//        while (pointSet.size() < pointsCount) {
//            double x = new Random().nextDouble() * radius;
//            double y = new Random().nextDouble() * radius;
//            Point testP = new Point(i, x, y);
//            if (MathUtilities.calculateDistance(middle, testP) < (double) radius / 3) {
//                pointSet.add(testP);
//                i++;
//            }
//
//        }
       return new ArrayList<>(pointSet);
    }

    public static List<Point> getRandomPoints(List<Point> points, int amount) {
        Set<Point> randomPoints = new LinkedHashSet<>();
        while (randomPoints.size() < amount) {
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
        }
        return new ArrayList<>(randomPoints);
    }

}
