package com.algorithms.ai.domain;

import com.algorithms.ai.techniques.FitnessTechnique;
import com.algorithms.ai.techniques.MutationTechnique;

import java.util.Random;

public class Chromosome<T> {

    private T gene;
    private double fitness;

    public Chromosome(T gene) {
        this.gene = gene;
    }

    public double calculateFitness(FitnessTechnique<T> fitnessTechnique) {
        fitness = fitnessTechnique.calculateFitness(gene);
        return fitness;
    }

    public void mutate(double mutationRate, MutationTechnique<T> mutationTechnique) {
        double chanceToMutate = new Random().nextDouble();
        if (chanceToMutate < mutationRate) {
            mutationTechnique.mutate(gene);
        }
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
