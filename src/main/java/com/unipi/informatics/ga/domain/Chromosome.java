package com.unipi.informatics.ga.domain;

import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.Random;

public class Chromosome<T> {

    private Dna<T> dna;
    private FitnessTechnique<T> fitnessTechnique;
    private double fitness;
    private double probability;

    public Chromosome(Dna<T> dna, FitnessTechnique<T> fitnessTechnique) {
        this.dna = dna;
        this.fitnessTechnique = fitnessTechnique;
    }

    public void mutate(double mutationRate, MutationTechnique<T> mutationTechnique) {
        double chanceToMutate = new Random().nextDouble();
        if (chanceToMutate < mutationRate) {
            this.dna.mutate(mutationTechnique);
        }
    }

        public void calculateFitness() {
            this.fitness = this.dna.calculateFitness(this.fitnessTechnique);
        }

    public Chromosome<T> getCopy() {
        return new Chromosome<>(dna, fitnessTechnique);
    }

    public Dna<T> getDna() {
        return dna;
    }

    public void setDna(Dna<T> dna) {
        this.dna = dna;
    }

    public FitnessTechnique<T> getFitnessTechnique() {
        return fitnessTechnique;
    }

    public void setFitnessTechnique(FitnessTechnique<T> fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chromosome)) return false;

        Chromosome<?> that = (Chromosome<?>) o;

        if (Double.compare(that.getFitness(), getFitness()) != 0) return false;
        if (Double.compare(that.getProbability(), getProbability()) != 0) return false;
        if (!getDna().equals(that.getDna())) return false;
        return getFitnessTechnique().equals(that.getFitnessTechnique());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getDna().hashCode();
        result = 31 * result + getFitnessTechnique().hashCode();
        temp = Double.doubleToLongBits(getFitness());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
