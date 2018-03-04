package com.unipi.informatics.convex_hull.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ConvexHullPoints")
public class ChromosomeConvexHullPoint implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @JsonIgnore
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
