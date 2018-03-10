package com.unipi.informatics.ga.domain;

import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

public abstract class Dna<T> {

    public abstract T getGene();

    public abstract int getIntersections();

    public double calculateFitness(FitnessTechnique<T> fitnessTechnique) {
        return fitnessTechnique.calculateFitness(this);
    }

    public Dna<T> mutate(MutationTechnique<T> mutationTechnique) {
        return mutationTechnique.execute(this);
    }
}
