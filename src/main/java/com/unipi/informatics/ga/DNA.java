package com.unipi.informatics.ga;

import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

public abstract class DNA<T> {

    public abstract T getGene();

    public abstract int getIntersections();

    public double calculateFitness(FitnessTechnique<T> fitnessTechnique) {
        return fitnessTechnique.calculateFitness(this);
    }

    public DNA<T> mutate(MutationTechnique<T> mutationTechnique) {
        return mutationTechnique.execute(this);
    }
}
