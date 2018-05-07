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

        if (getLabel() != point.getLabel()) return false;
        if (Double.compare(point.getX(), getX()) != 0) return false;
        return Double.compare(point.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getLabel();
        temp = Double.doubleToLongBits(getX());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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
