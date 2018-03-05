package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Population;

import java.util.List;

class CH_Population<T> extends Population<T> {

    CH_Population(List<Chromosome<T>> chromosomes) {
        super(chromosomes);
    }

}
