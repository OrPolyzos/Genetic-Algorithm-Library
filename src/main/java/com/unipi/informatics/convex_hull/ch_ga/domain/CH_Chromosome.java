package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.techniques.FitnessTechnique;

class CH_Chromosome<T> extends Chromosome<T> {

    CH_Chromosome(DNA<T> DNA, FitnessTechnique<T> fitnessTechnique) {
        super(DNA, fitnessTechnique);
    }

}
