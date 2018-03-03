package ga.domain;

import ga.DNA;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;

import java.util.Map;
import java.util.Random;

public class Chromosome {

    private DNA DNA;
    private double fitness;
    private double probability;

    public Chromosome(DNA DNA) {
        this.DNA = DNA;
        fitness = DNA.calculateFitness();
    }

    public Chromosome getCopy() {
        return new Chromosome(this.DNA);
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
            fitness = DNA.calculateFitness();
        }
        return this;
    }
}
