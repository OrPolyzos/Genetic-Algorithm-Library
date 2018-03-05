package com.unipi.informatics.ga.domain;

import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.Random;

public class Chromosome<T> {

    public DNA<T> DNA;
    private FitnessTechnique<T> fitnessTechnique;
    private double fitness;
    private double probability;

    public Chromosome(DNA<T> DNA, FitnessTechnique<T> fitnessTechnique) {
        this.DNA = DNA;
        this.fitnessTechnique = fitnessTechnique;
        fitness = DNA.calculateFitness(fitnessTechnique);
    }

    public Chromosome<T> getCopy() {
        return new Chromosome<>(this.DNA, fitnessTechnique);
    }

    public DNA<T> getDNA() {
        return DNA;
    }

    public void setDNA(DNA<T> DNA) {
        this.DNA = DNA;
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

    Chromosome<T> mutate(double mutationRate, MutationTechnique<T> mutationTechnique) {
        double chanceToMutate = new Random().nextDouble();
        if (chanceToMutate < mutationRate) {
            DNA= DNA.mutate(mutationTechnique);
            fitness = DNA.calculateFitness(fitnessTechnique);
        }
        return this;
    }

    public FitnessTechnique<T> getFitnessTechnique() {
        return fitnessTechnique;
    }

}
