package com.unipi.informatics.convex_hull.dao;

import javax.persistence.*;

@Entity
@Table(name = "ConvexHullPoints")
public class ChromosomeConvexHullPoint {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chromosome_id", referencedColumnName = "id")
    private ChromosomeDAO chromosomeDAO;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    private PointDAO pointDAO;

    public ChromosomeDAO getChromosomeDAO() {
        return chromosomeDAO;
    }

    public void setChromosomeDAO(ChromosomeDAO chromosomeDAO) {
        this.chromosomeDAO = chromosomeDAO;
    }

    public PointDAO getPointDAO() {
        return pointDAO;
    }

    public void setPointDAO(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }
}
