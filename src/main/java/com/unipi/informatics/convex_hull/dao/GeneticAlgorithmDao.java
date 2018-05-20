package com.unipi.informatics.convex_hull.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "GeneticAlgorithms")
public class GeneticAlgorithmDao implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "populationCount")
    private int population;
    @Column(name = "mutationRate")
    private double mutationRate;
    @Column(name = "generations")
    private int generations;
    @Column(name = "duration")
    private double duration;
    @Column(name = "pointsSize")
    private int pointsSize;
    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneticAlgorithmDao", targetEntity = ChromosomeDao.class)
    private List<ChromosomeDao> fittestChromosomes;
    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneticAlgorithmDao", targetEntity = PointDao.class)
    private List<PointDao> points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
