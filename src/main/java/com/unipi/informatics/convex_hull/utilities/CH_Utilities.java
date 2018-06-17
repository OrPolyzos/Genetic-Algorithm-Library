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
//        List<Point> list = new ArrayList<>();
//        list.add(new Point(73, 192.94640684417507, 59.463104772504714));
//        list.add(new Point(82, 22.52119444591316, 54.65431163610951));
//        list.add(new Point(13, 9.322545469832077, 111.52387112691386));
//        list.add(new Point(34, 16.542186715818808, 380.45321693293556));
//        list.add(new Point(67, 36.64245380149978, 437.1928357715704));
//        list.add(new Point(98, 65.23712649671192, 458.19416686952553));
//        list.add(new Point(77, 372.0513086564494, 469.38657738549796));
//        list.add(new Point(47, 462.99146130825477, 428.1466196045406));
//        list.add(new Point(18, 475.8231495562361, 198.7001651434513));
//        list.add(new Point(86, 461.67260422669807, 66.32322239875344));
//        list.add(new Point(7, 450.796423918063, 57.983741762959184));
//        list.add(new Point(8, 390.93722029977505, 64.63096473691732));
//        list.add(new Point(16, 377.481869796276, 72.02191811394381));
//        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public static List<Point> getRandomPoints(List<Point> points, int amount) {
        Set<Point> randomPoints = new LinkedHashSet<>();
        while (randomPoints.size() < amount) {
            randomPoints.add(points.get(new Random().nextInt(points.size())));
        }
        return new ArrayList<>(randomPoints);
    }

    public static List<Point> calculateOutsidePoints(List<Point> convexHull, List<Point> points) {
        List<Point> outsidePoints = new ArrayList<>();
        for (Point point : points) {
            if (!MathUtilities.isInside(convexHull, point) && !convexHull.contains(point)) {
                outsidePoints.add(point);
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
                    intersectionsList.add(convexHull.get(i));
                    intersectionsList.add(convexHull.get(currentEdgeEnd));
                    intersectionsList.add(convexHull.get(j));
                    intersectionsList.add(convexHull.get(testEdgeEnd));
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
                intersectionsList.add(convexHull.get(currentEdgeStart));
                intersectionsList.add(convexHull.get(currentEdgeEnd));
                intersectionsList.add(convexHull.get(i));
                intersectionsList.add(convexHull.get(testEdgeEnd));
                intersectionsLists.add(intersectionsList);
            }
        }
        return intersectionsLists;
    }

    public static List<Point> calculateSickJoints(List<Point> convexHull) {
        if (convexHull.size() < 3) {
            return convexHull;
        }
        int winding = MathUtilities.calculateWinding(convexHull);
        List<Point> sickJoints = new ArrayList<>();
        int sickNumber = winding == 1 ? 1 : 2;
        for (int i = 0; i < convexHull.size(); i++) {
            Point p0 = convexHull.get(i);
            Point p1 = i <= convexHull.size() - 2 ? convexHull.get(i + 1) : convexHull.get(0);
            Point p2 = i < convexHull.size() - 2 ? convexHull.get(i + 2) : i == convexHull.size() - 2 ? convexHull.get(0) : convexHull.get(1);

            if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                sickJoints.add(p1);
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
