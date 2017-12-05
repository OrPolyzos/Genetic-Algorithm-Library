package ga;

import java.util.Random;

public abstract class Chromosome implements Comparable<Chromosome> {

    private DNA DNA;
    private double fitness;
    private double probability;

    public Chromosome(DNA DNA) {
        this.DNA = DNA;
        fitness = DNA.calculateFitness();
    }

    public abstract Chromosome getCopy();

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
            this.DNA = DNA.mutate();
            fitness = DNA.calculateFitness();
        }
        return this;
    }


    public int compareTo(Chromosome otherChromosome) {
        if (this.getFitness() > otherChromosome.getFitness()) {
            return 1;
        } else if (this.getFitness() < otherChromosome.getFitness()) {
            return -1;
        } else {
            if (this.getDNA().getOutsidePoints().size() < otherChromosome.getDNA().getOutsidePoints().size()) {
                return 1;
            } else if (this.getDNA().getOutsidePoints().size() > otherChromosome.getDNA().getOutsidePoints().size()) {
                return -1;
            } else {
                if (this.getDNA().getGene().size() < otherChromosome.getDNA().getGene().size()) {
                    return 1;
                } else if (this.getDNA().getGene().size() > otherChromosome.getDNA().getGene().size()) {
                    return -1;
                } else return 0;
            }
        }
    }
}
