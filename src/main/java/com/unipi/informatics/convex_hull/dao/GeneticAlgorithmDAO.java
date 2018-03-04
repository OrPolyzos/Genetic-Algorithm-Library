package com.unipi.informatics.convex_hull.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GeneticAlgorithm")
public class GeneticAlgorithmDAO {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "populationCount", nullable = false)
    private int population;
    @Column(name = "mutationRate", nullable = false)
    private double mutationRate;
    @Column(name = "generations", nullable = false)
    private int generations;
    @Column(name = "duration", nullable = false, precision = 10, scale = 2)
    private double duration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneticAlgorithmDAO", targetEntity = ChromosomeDAO.class)
    private List<ChromosomeDAO> fittestChromosomes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geneticAlgorithmDAO", targetEntity = PointDAO.class)
    private List<PointDAO> points;

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

    public List<ChromosomeDAO> getFittestChromosomes() {
        return fittestChromosomes;
    }

    public void setFittestChromosomes(List<ChromosomeDAO> fittestChromosomes) {
        this.fittestChromosomes = fittestChromosomes;
    }

    public List<PointDAO> getPoints() {
        return points;
    }

    public void setPoints(List<PointDAO> points) {
        this.points = points;
    }
}
