package com.algorithms.ai.domain;

public class Chromosome<T> {

    private T gene;
    private double fitness;

    public Chromosome(T gene) {
        this.gene = gene;
    }


    public T getGene() {
        return gene;
    }

    public void setGene(T gene) {
        this.gene = gene;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
