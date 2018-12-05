package com.algorithms.ai.domain;

import com.algorithms.ai.techniques.FitnessTechnique;
import com.algorithms.ai.techniques.MutationTechnique;

import java.util.Random;

public class Chromosome<T> {

    private Dna<T> dna;
    private double fitness;

    public Chromosome(Dna<T> dna) {
        this.dna = dna;
    }

    public double calculateFitness(FitnessTechnique<T> fitnessTechnique) {
        fitness = dna.calculateFitness(fitnessTechnique);
        return fitness;
    }

    public void mutate(double mutationRate, MutationTechnique<T> mutationTechnique) {
        double chanceToMutate = new Random().nextDouble();
        if (chanceToMutate < mutationRate) {
            dna.mutate(mutationTechnique);
        }
    }

    public Dna<T> getDna() {
        return dna;
    }

    public void setDna(Dna<T> dna) {
        this.dna = dna;
    }


    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
