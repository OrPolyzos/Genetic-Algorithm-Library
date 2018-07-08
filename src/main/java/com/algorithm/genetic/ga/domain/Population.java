package com.algorithm.genetic.ga.domain;

import java.util.List;

public class Population<T> {

    private List<Chromosome<T>> chromosomes;
    private Chromosome<T> fittestChromosome;

    public Population(List<Chromosome<T>> chromosomes) {
        this.chromosomes = chromosomes;
        fittestChromosome = findFittestChromosome();

    }

    public Chromosome<T> findFittestChromosome() {
        double fitnessesSum = 0;
        Chromosome<T> fittestChromosome = chromosomes.get(0);
        double bestFitness = chromosomes.get(0).getFitness();
        for (Chromosome<T> chromosome : chromosomes) {
            chromosome.calculateFitness();
            fitnessesSum += chromosome.getFitness();
            if (chromosome.getFitness() > bestFitness) {
                fittestChromosome = chromosome;
                bestFitness = chromosome.getFitness();
            }
        }
        for (Chromosome<T> chromosome : chromosomes) {
            double probability = chromosome.getFitness() / fitnessesSum;
            chromosome.setProbability(probability);
        }
        return fittestChromosome;
    }

    public List<Chromosome<T>> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<Chromosome<T>> chromosomes) {
        this.chromosomes = chromosomes;
        fittestChromosome = findFittestChromosome();
    }

    public Chromosome<T> getFittestChromosome() {
        return fittestChromosome;
    }

    public void setFittestChromosome(Chromosome<T> fittestChromosome) {
        this.fittestChromosome = fittestChromosome;
    }
}
