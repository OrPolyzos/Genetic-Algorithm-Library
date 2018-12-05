package com.algorithms.ai.techniques;

import com.algorithms.ai.domain.Dna;

public interface FitnessTechnique<T> {

    double calculateFitness(Dna<T> dna);
}
