package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.FitnessTechnique;

class CH_Chromosome extends Chromosome<CH_Gene> {

    CH_Chromosome(Dna<CH_Gene> dna, FitnessTechnique<CH_Gene> fitnessTechnique) {
        super(dna, fitnessTechnique);
    }
}
