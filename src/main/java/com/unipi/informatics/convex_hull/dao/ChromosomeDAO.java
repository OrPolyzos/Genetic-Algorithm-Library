package com.unipi.informatics.convex_hull.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chromosomes")
public class ChromosomeDAO implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "fitness", precision = 10, scale = 2)
    private double fitness;
    @Column(name = "fitnessTechnique")
    private String fitnessTechnique;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genetic_algorithm_id", referencedColumnName = "id")
    private GeneticAlgorithmDAO geneticAlgorithmDAO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chromosomeDAO", targetEntity = ChromosomeConvexHullPoint.class)
    private List<ChromosomeConvexHullPoint> chromosomeConvexHullPoints = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public GeneticAlgorithmDAO getGeneticAlgorithmDAO() {
        return geneticAlgorithmDAO;
    }

    public void setGeneticAlgorithmDAO(GeneticAlgorithmDAO geneticAlgorithmDAO) {
        this.geneticAlgorithmDAO = geneticAlgorithmDAO;
    }

    public List<ChromosomeConvexHullPoint> getChromosomeConvexHullPoints() {
        return chromosomeConvexHullPoints;
    }

    public void setChromosomeConvexHullPoints(List<ChromosomeConvexHullPoint> chromosomeConvexHullPoints) {
        this.chromosomeConvexHullPoints = chromosomeConvexHullPoints;
    }
}
