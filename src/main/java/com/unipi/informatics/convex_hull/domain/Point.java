package com.unipi.informatics.convex_hull.domain;

public class Point implements Cloneable {

    private int label;
    private double x;
    private double y;

    public Point(int label, double x, double y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public Point(Point otherPoint) {
        this.label = otherPoint.getLabel();
        this.x = otherPoint.getX();
        this.y = otherPoint.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        return getLabel() == point.getLabel();
    }

    @Override
    public int hashCode() {
        return getLabel();
    }

    public int getLabel() {
        return label;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "label=" + label +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
