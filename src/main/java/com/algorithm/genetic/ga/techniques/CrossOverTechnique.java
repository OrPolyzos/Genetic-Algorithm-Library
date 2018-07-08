package com.algorithm.genetic.ga.techniques;

import com.algorithm.genetic.ga.domain.Chromosome;

public interface CrossOverTechnique<T> {

    Chromosome<T> crossOver(Chromosome<T> parentA, Chromosome<T> parentB);
}
