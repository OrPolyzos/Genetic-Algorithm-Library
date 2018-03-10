package com.unipi.informatics.convex_hull.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ConvexHullPoints")
public class CH_PointDao implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chromosome_id", referencedColumnName = "id")
    private ChromosomeDao chromosomeDao;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    private PointDao pointDao;

    public ChromosomeDao getChromosomeDao() {
        return chromosomeDao;
    }

    public void setChromosomeDao(ChromosomeDao chromosomeDao) {
        this.chromosomeDao = chromosomeDao;
    }

    public PointDao getPointDao() {
        return pointDao;
    }

    public void setPointDao(PointDao pointDao) {
        this.pointDao = pointDao;
    }
}
