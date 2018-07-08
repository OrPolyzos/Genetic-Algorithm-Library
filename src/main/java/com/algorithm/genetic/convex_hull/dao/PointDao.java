package com.algorithm.genetic.convex_hull.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PointDao implements Serializable {

    private int label;
    private double x;
    private double y;
    @JsonIgnore
    private GeneticAlgorithmDao geneticAlgorithmDao;
    @JsonIgnore
    private List<CH_PointDao> chromosomeConvexHullPoints = new ArrayList<>();

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public GeneticAlgorithmDao getGeneticAlgorithmDao() {
        return geneticAlgorithmDao;
    }

    public void setGeneticAlgorithmDao(GeneticAlgorithmDao geneticAlgorithmDao) {
        this.geneticAlgorithmDao = geneticAlgorithmDao;
    }

    public List<CH_PointDao> getChromosomeConvexHullPoints() {
        return chromosomeConvexHullPoints;
    }

    public void setChromosomeConvexHullPoints(List<CH_PointDao> chromosomeConvexHullPoints) {
        this.chromosomeConvexHullPoints = chromosomeConvexHullPoints;
    }
}
