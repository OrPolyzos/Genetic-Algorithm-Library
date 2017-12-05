package ga.domain;

import java.util.List;

public class Population {

    private List<Chromosome> chromosomes;
    private Chromosome fittestChromosome;

    private GeneticAlgorithm geneticAlgorithm;

    public Population(List<Chromosome> chromosomes, GeneticAlgorithm geneticAlgorithm) {
        this.chromosomes = chromosomes;
        this.geneticAlgorithm = geneticAlgorithm;
        fittestChromosome = findFittestChromosome();
        calculateProbabilities();
    }

    public Population getCopy() {
        return new Population(this.chromosomes, this.geneticAlgorithm);
    }

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

    public GeneticAlgorithm getGeneticAlgorithm() {
        return geneticAlgorithm;
    }

    public void setGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm) {
        this.geneticAlgorithm = geneticAlgorithm;
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

}
