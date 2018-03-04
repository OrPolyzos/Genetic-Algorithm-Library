package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.DNA;

public interface FitnessTechnique {
    double calculateFitness(DNA dna);
}
