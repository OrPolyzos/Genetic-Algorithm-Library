package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.DNA;

public interface FitnessTechnique<T> {
    double calculateFitness(DNA<T> dna);
}
