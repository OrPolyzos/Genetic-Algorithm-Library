package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;

import java.util.List;

public class CH_Gene {

    private List<Point> points;
    private List<Point> convexHull;
    private List<Point> outsidePoints;
    private List<Point> sickJoints;
    private List<Point> intersectionPoints;

    public CH_Gene(List<Point> points, List<Point> convexHull) {
        this.points = points;
        this.convexHull = convexHull;
        this.outsidePoints = CH_Utilities.calculateOutsidePoints(convexHull, points);
        this.sickJoints = CH_Utilities.calculateSickJoints(convexHull);
        this.intersectionPoints = CH_Utilities.calculateIntersections(convexHull);
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Point> getConvexHull() {
        return convexHull;
    }

    public List<Point> getOutsidePoints() {
        return outsidePoints;
    }

    public List<Point> getSickJoints() {
        return sickJoints;
    }

    public List<Point> getIntersectionPoints() {
        return intersectionPoints;
    }
}
