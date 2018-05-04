package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Population;

import java.util.List;

class CH_Population extends Population<CH_Gene> {

    CH_Population(List<Chromosome<CH_Gene>> chromosomes) {
        super(chromosomes);
    }
}
