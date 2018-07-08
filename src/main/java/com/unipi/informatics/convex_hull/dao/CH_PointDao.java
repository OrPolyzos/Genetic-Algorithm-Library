package com.unipi.informatics.convex_hull.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class CH_PointDao implements Serializable {

    @JsonIgnore
    private ChromosomeDao chromosomeDao;
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
