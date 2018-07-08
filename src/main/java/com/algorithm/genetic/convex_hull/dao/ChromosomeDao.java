package com.algorithm.genetic.convex_hull.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChromosomeDao implements Serializable {

    private double fitness;
    private String fitnessTechnique;
    @JsonIgnore
    private GeneticAlgorithmDao geneticAlgorithmDao;
    private List<CH_PointDao> ch_points = new ArrayList<>();

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String getFitnessTechnique() {
        return fitnessTechnique;
    }

    public void setFitnessTechnique(String fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;
    }

    public GeneticAlgorithmDao getGeneticAlgorithmDao() {
        return geneticAlgorithmDao;
    }

    public void setGeneticAlgorithmDao(GeneticAlgorithmDao geneticAlgorithmDao) {
        this.geneticAlgorithmDao = geneticAlgorithmDao;
    }

    public List<CH_PointDao> getCh_points() {
        return ch_points;
    }

    public void setCh_points(List<CH_PointDao> ch_points) {
        this.ch_points = ch_points;
    }
}
