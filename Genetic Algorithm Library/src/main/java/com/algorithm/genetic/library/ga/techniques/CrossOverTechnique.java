package com.algorithm.genetic.library.ga.techniques;

import com.algorithm.genetic.library.ga.domain.Chromosome;

public interface CrossOverTechnique<T> {

    Chromosome<T> crossOver(Chromosome<T> parentA, Chromosome<T> parentB);
}
