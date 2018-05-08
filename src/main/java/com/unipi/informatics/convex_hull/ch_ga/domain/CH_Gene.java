package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;

import java.util.ArrayList;
import java.util.List;

public class CH_Gene {

    private List<Point> points;
    private List<Point> convexHull;
    private List<Point> outsidePoints;
    private List<Point> sickJoints;
    private List<List<Point>> intersectionPoints;

    public CH_Gene(List<Point> points, List<Point> convexHull) {
        this.points = points;
        this.convexHull = convexHull;
        updateLists();
    }

    public CH_Gene getCopy() {
        List<Point> convexHullCopy = new ArrayList<>(this.convexHull);
        return new CH_Gene(this.points,convexHullCopy);
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Point> getConvexHull() {
        return convexHull;
    }

    public void setConvexHull(List<Point> convexHull) {
        this.convexHull = convexHull;
        updateLists();
    }

    public List<Point> getOutsidePoints() {
        return outsidePoints;
    }

    public List<Point> getSickJoints() {
        return sickJoints;
    }

    public List<List<Point>> getIntersectionPoints() {
        return intersectionPoints;
    }

    public void updateLists() {
            this.outsidePoints = CH_Utilities.calculateOutsidePoints(convexHull, points);
            this.sickJoints = CH_Utilities.calculateSickJoints(convexHull);
            this.intersectionPoints = CH_Utilities.calculateIntersections(convexHull);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CH_Gene)) return false;

        CH_Gene ch_gene = (CH_Gene) o;

        if (!getPoints().equals(ch_gene.getPoints())) return false;
        if (!getConvexHull().equals(ch_gene.getConvexHull())) return false;
        if (getOutsidePoints() != null ? !getOutsidePoints().equals(ch_gene.getOutsidePoints()) : ch_gene.getOutsidePoints() != null)
            return false;
        if (getSickJoints() != null ? !getSickJoints().equals(ch_gene.getSickJoints()) : ch_gene.getSickJoints() != null)
            return false;
        return getIntersectionPoints() != null ? getIntersectionPoints().equals(ch_gene.getIntersectionPoints()) : ch_gene.getIntersectionPoints() == null;
    }

    @Override
    public int hashCode() {
        int result = getPoints().hashCode();
        result = 31 * result + getConvexHull().hashCode();
        result = 31 * result + (getOutsidePoints() != null ? getOutsidePoints().hashCode() : 0);
        result = 31 * result + (getSickJoints() != null ? getSickJoints().hashCode() : 0);
        result = 31 * result + (getIntersectionPoints() != null ? getIntersectionPoints().hashCode() : 0);
        return result;
    }
}
