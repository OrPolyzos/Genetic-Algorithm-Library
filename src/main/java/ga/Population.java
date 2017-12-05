package ga;

import java.util.List;
import java.util.Random;

public abstract class Population {

    private List<Chromosome> chromosomes;
    private Chromosome fittestChromosome;

    public Population(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
        fittestChromosome = findFittestChromosome();
        calculateProbabilities();
    }

    public abstract Population getCopy();

    private Chromosome findFittestChromosome() {
        double bestFitness = Double.MIN_VALUE;
        Chromosome fittestChromosome = null;

        for (int i = 0; i < chromosomes.size(); i++) {
            if (chromosomes.get(i).getFitness() > bestFitness) {
                fittestChromosome = chromosomes.get(i);
                bestFitness = chromosomes.get(i).getFitness();
            }
        }
        return fittestChromosome;
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public Chromosome getFittestChromosome() {
        return fittestChromosome;
    }

    private void calculateProbabilities() {
        double fitnessesSum = 0;
        for (int i = 0; i < chromosomes.size(); i++) {
            fitnessesSum += chromosomes.get(i).getFitness();
        }
        //Calculate probabilities
        for (int i = 0; i < chromosomes.size(); i++) {
            double probability = chromosomes.get(i).getFitness() / fitnessesSum;
            chromosomes.get(i).setProbability(probability);
        }
    }

    public Chromosome pickOne() {
        int index = 0;
        double r = new Random().nextDouble();
        while (r > 0) {
            r = r - chromosomes.get(index).getProbability();
            index++;
        }
        index--;
        return chromosomes.get(index);
    }

}
