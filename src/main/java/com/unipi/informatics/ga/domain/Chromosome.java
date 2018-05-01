package com.unipi.informatics.ga.domain;

import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.Random;

public class Chromosome<T> {

    private Dna<T> Dna;
    private FitnessTechnique<T> fitnessTechnique;
    private double fitness;
    private double probability;

    public Chromosome(Dna<T> Dna, FitnessTechnique<T> fitnessTechnique) {
        this.Dna = Dna;
        this.fitnessTechnique = fitnessTechnique;
        fitness = Dna.calculateFitness(fitnessTechnique);
    }

    Chromosome<T> mutate(double mutationRate, MutationTechnique<T> mutationTechnique) {
        double chanceToMutate = new Random().nextDouble();
        if (chanceToMutate < mutationRate) {
            Dna = Dna.mutate(mutationTechnique);
            fitness = Dna.calculateFitness(fitnessTechnique);
        }
        return this;
    }

    public Chromosome<T> getCopy() {
        return new Chromosome<>(this.Dna, fitnessTechnique);
    }

    public Dna<T> getDna() {
        return Dna;
    }

    public void setDna(Dna<T> dna) {
        this.Dna = dna;
    }

    public double getFitness() {
        return fitness;
    }

    public double getProbability() {
        return probability;
    }

    void setProbability(double probability) {
        this.probability = probability;
    }

    public FitnessTechnique<T> getFitnessTechnique() {
        return fitnessTechnique;
    }

}
