package com.unipi.informatics.convex_hull.utilities;

import com.unipi.informatics.convex_hull.domain.Point;

import java.util.List;

class MathUtilities {

    static double calculateDistance(Point p, Point q) {
        return Math.sqrt(Math.pow((p.getX() - q.getX()), 2) + Math.pow((p.getY() - q.getY()), 2));
    }

    static int orientation(Point p, Point q, Point r) {
        double result = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
        //Returning 0 for collinear points
        if (result == 0) return 0;

        //Returning 1 for clockwise orientation or 2 for counter-clockwise
        return (result > 0) ? 1 : 2;
    }

    static boolean onSegment(Point p, Point q, Point r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
    }

    static int calculateWinding(List<Point> polygon) {
        int sum = 0;
        for (int i = 0; i < polygon.size(); i++) {
            Point p1 = polygon.get(i);
            Point p2 = i < polygon.size() - 1 ? polygon.get(i + 1) : polygon.get(0);
            sum += (p1.getX() * p2.getY()) - (p2.getX() * p1.getY());
        }
        return (sum > 0) ? 1 : -1;
    }

    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4) return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and p2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

    static boolean isInside(List<Point> polygon, Point checkPoint) {
        // There must be at least 3 vertices in polygon[]
        if (polygon.size() < 3) return false;
        Point extremePoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE, checkPoint.getY());
        // Count intersections of the above line with sides of polygon
        int count = 0;
        int i = 0;
        do {
            int next = (i + 1) % polygon.size();
            // Check if the line segment from 'p' to 'extreme' intersects
            // with the line segment from 'polygon[i]' to 'polygon[next]'

            if (doIntersect(polygon.get(i), polygon.get(next), checkPoint, extremePoint)) {
                // If the point 'p' is colinear with line segment 'i-next',
                // then check if it lies on segment. If it lies, return true,
                // otherwise false
                if (orientation(polygon.get(i), checkPoint, polygon.get(next)) == 0)
                    return onSegment(polygon.get(i), checkPoint, polygon.get(next));
                count++;
            }
            i = next;
        } while (i != 0);
        // Return true if count is odd, false otherwise
        return (count % 2 == 1);
    }

}
