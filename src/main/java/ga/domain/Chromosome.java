package ga.domain;

import java.util.Random;

public class Chromosome {

    private ga.domain.DNA DNA;
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

    public Chromosome mutate(double mutationRate) {
        double chanceToMutate = new Random().nextDouble();
        if (chanceToMutate < mutationRate) {
            DNA = DNA.mutate();
            fitness = DNA.calculateFitness();
        }
        return this;
    }
}
