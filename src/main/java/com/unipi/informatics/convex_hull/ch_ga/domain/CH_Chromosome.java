package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.techniques.FitnessTechnique;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class CH_Chromosome extends Chromosome {

    public CH_Chromosome(DNA DNA, FitnessTechnique fitnessTechnique) {
        super(DNA, fitnessTechnique);
    }

}
