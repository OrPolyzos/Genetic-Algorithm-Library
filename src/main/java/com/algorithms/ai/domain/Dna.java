package com.algorithms.ai.domain;

import com.algorithms.ai.techniques.FitnessTechnique;
import com.algorithms.ai.techniques.MutationTechnique;

public class Dna<T> {

    private T gene;

    public Dna(T gene) {
        this.gene = gene;
    }

    public T getGene() {
        return gene;
    }

    public void setGene(T gene) {
        this.gene = gene;
    }

    public double calculateFitness(FitnessTechnique<T> fitnessTechnique) {
        return fitnessTechnique.calculateFitness(this);
    }

    public void mutate(MutationTechnique<T> mutationTechnique) {
        mutationTechnique.mutate(this);
    }
}
