package com.unipi.informatics.convex_hull.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Points")
public class PointDao implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "label")
    private int label;
    @Column(name = "x", precision = 10, scale = 2)
    private double x;
    @Column(name = "y", precision = 10, scale = 2)
    private double y;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genetic_algorithm_id", referencedColumnName = "id")
    private GeneticAlgorithmDao geneticAlgorithmDao;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pointDao", targetEntity = CH_PointDao.class)
    private List<CH_PointDao> chromosomeConvexHullPoints = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
