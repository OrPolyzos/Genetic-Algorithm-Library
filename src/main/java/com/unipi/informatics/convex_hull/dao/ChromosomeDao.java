package com.unipi.informatics.convex_hull.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chromosomes")
public class ChromosomeDao implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "fitness", precision = 10, scale = 2)
    private double fitness;
    @Column(name = "fitnessTechnique")
    private String fitnessTechnique;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genetic_algorithm_id", referencedColumnName = "id")
    private GeneticAlgorithmDao geneticAlgorithmDao;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "chromosomeDao", targetEntity = CH_PointDao.class)
    private List<CH_PointDao> ch_points = new ArrayList<>();

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
