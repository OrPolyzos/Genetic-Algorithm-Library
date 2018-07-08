package com.algorithm.genetic.ga.domain;

import com.algorithm.genetic.ga.techniques.FitnessTechnique;
import com.algorithm.genetic.ga.techniques.MutationTechnique;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dna)) return false;

        Dna<?> dna = (Dna<?>) o;

        return getGene().equals(dna.getGene());
    }

    @Override
    public int hashCode() {
        return getGene().hashCode();
    }
}
