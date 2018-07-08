package com.algorithm.genetic.convex_hull.dao;

import java.io.Serializable;
import java.util.List;

public class GeneticAlgorithmDao implements Serializable {

    private int population;
    private double mutationRate;
    private int generations;
    private double duration;
    private int pointsSize;
    private List<ChromosomeDao> fittestChromosomes;
    private List<PointDao> points;

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getPointsSize() {
        return pointsSize;
    }

    public void setPointsSize(int pointsSize) {
        this.pointsSize = pointsSize;
    }

    public List<ChromosomeDao> getFittestChromosomes() {
        return fittestChromosomes;
    }

    public void setFittestChromosomes(List<ChromosomeDao> fittestChromosomes) {
        this.fittestChromosomes = fittestChromosomes;
    }

    public List<PointDao> getPoints() {
        return points;
    }

    public void setPoints(List<PointDao> points) {
        this.points = points;
    }
}
