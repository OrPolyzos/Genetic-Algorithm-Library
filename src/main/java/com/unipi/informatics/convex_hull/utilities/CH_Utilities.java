package com.unipi.informatics.convex_hull.utilities;

import com.unipi.informatics.convex_hull.domain.Point;

import java.util.*;

public class CH_Utilities {

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
        return new ArrayList<>(pointSet);
    }

    public static List<Point> getRandomPoints(List<Point> points, int amount) {
        Set<Point> randomPoints = new LinkedHashSet<>();
        while (randomPoints.size() < amount) {
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
        }
        return new ArrayList<>(randomPoints);
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

    public static List<List<Point>> calculateIntersections(List<Point> convexHull) {
        List<List<Point>> intersectionsLists = new ArrayList<>();
        for (int i = 0; i < convexHull.size() - 1; i++) {
            int currentEdgeEnd = i + 1;
            for (int j = currentEdgeEnd + 1; j < convexHull.size() - 1; j++) {
                List<Point> intersectionsList = new ArrayList<>();
                int testEdgeEnd = j + 1;
                if (MathUtilities.doIntersect(convexHull.get(i), convexHull.get(currentEdgeEnd),
                        convexHull.get(j), convexHull.get(testEdgeEnd))) {
                    intersectionsList.add(new Point(convexHull.get(i)));
                    intersectionsList.add(new Point(convexHull.get(currentEdgeEnd)));
                    intersectionsList.add(new Point(convexHull.get(j)));
                    intersectionsList.add(new Point(convexHull.get(testEdgeEnd)));
                    intersectionsLists.add(intersectionsList);
                }
            }
        }
        int currentEdgeStart = convexHull.size() - 1;
        int currentEdgeEnd = 0;
        for (int i = 1; i < convexHull.size() - 2; i++) {
            List<Point> intersectionsList = new ArrayList<>();
            int testEdgeEnd = i + 1;
            if (MathUtilities.doIntersect(convexHull.get(currentEdgeStart), convexHull.get(currentEdgeEnd),
                    convexHull.get(i), convexHull.get(testEdgeEnd))) {
                intersectionsList.add(new Point(convexHull.get(currentEdgeStart)));
                intersectionsList.add(new Point(convexHull.get(currentEdgeEnd)));
                intersectionsList.add(new Point(convexHull.get(i)));
                intersectionsList.add(new Point(convexHull.get(testEdgeEnd)));
                intersectionsLists.add(intersectionsList);
            }
        }
        return intersectionsLists;
    }

    public static List<Point> calculateSickJoints(List<Point> convexHull) {
        int winding = MathUtilities.calculateWinding(convexHull);
        List<Point> sickJoints = new ArrayList<>();
        int sickNumber = winding == 1 ? 1 : 2;
        for (int i = 0; i < convexHull.size(); i++) {
            Point p0 = convexHull.get(i);
            Point p1 = i <= convexHull.size() - 2 ? convexHull.get(i + 1) : convexHull.get(0);
            Point p2 = i < convexHull.size() - 2 ? convexHull.get(i + 2) : i == convexHull.size() - 2 ? convexHull.get(0) : convexHull.get(1);

            if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                sickJoints.add(new Point(p1));
            }
        }
        return sickJoints;
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

}
