package com.algorithm.genetic.ga.techniques;

import com.algorithm.genetic.ga.domain.Dna;

public interface FitnessTechnique<T> {

    double calculateFitness(Dna<T> dna);
}
