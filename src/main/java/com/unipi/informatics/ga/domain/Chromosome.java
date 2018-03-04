package com.unipi.informatics.ga.domain;

import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;

public class Chromosome{


    public DNA DNA;
    private FitnessTechnique fitnessTechnique;
    private double fitness;
    private double probability;

    public Chromosome(DNA DNA, FitnessTechnique fitnessTechnique) {
        this.DNA = DNA;
        this.fitnessTechnique = fitnessTechnique;
        fitness = DNA.calculateFitness(fitnessTechnique);
    }

    public Chromosome getCopy() {
        return new Chromosome(this.DNA, fitnessTechnique);
    }

    public DNA getDNA() {
        return DNA;
    }

    public double getFitness() {
        return fitness;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Chromosome mutate(double mutationRate, MutationTechnique mutationTechnique) {
        double chanceToMutate = new Random().nextDouble();
        if (chanceToMutate < mutationRate) {
            DNA = DNA.mutate(mutationTechnique);
            fitness = DNA.calculateFitness(fitnessTechnique);
        }
        return this;
    }

    public void setDNA(com.unipi.informatics.ga.DNA DNA) {
        this.DNA = DNA;
    }

    public FitnessTechnique getFitnessTechnique() {
        return fitnessTechnique;
    }

    public void setFitnessTechnique(FitnessTechnique fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
