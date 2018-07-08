package com.algorithm.genetic.library.ga.techniques;

import com.algorithm.genetic.library.ga.domain.Dna;

public interface FitnessTechnique<T> {

    double calculateFitness(Dna<T> dna);
}
