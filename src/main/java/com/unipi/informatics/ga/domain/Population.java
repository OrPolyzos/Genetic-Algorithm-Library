package com.unipi.informatics.ga.domain;

import java.util.List;

public class Population<T> {

    private List<Chromosome<T>> chromosomes;
    private Chromosome<T> fittestChromosome;

    public Population(List<Chromosome<T>> chromosomes) {
        this.chromosomes = chromosomes;
        fittestChromosome = findFittestChromosome();
        calculateProbabilities();
    }

    private Chromosome<T> findFittestChromosome() {
        double bestFitness = Double.MIN_VALUE;
        Chromosome<T> fittestChromosome = null;

        for (int i = 0; i < chromosomes.size(); i++) {
            if (chromosomes.get(i).getFitness() > bestFitness) {
                fittestChromosome = chromosomes.get(i);
                bestFitness = chromosomes.get(i).getFitness();
            }
        }
        return fittestChromosome;
    }

    public List<Chromosome<T>> getChromosomes() {
        return chromosomes;
    }

    public Chromosome<T> getFittestChromosome() {
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

}
