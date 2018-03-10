package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.domain.Dna;

public interface FitnessTechnique<T> {
    double calculateFitness(Dna<T> dna);
}
