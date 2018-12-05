package com.algorithms.ai.domain;

import com.algorithms.ai.exception.GeneticAlgorithmException;
import com.algorithms.ai.techniques.FitnessTechnique;

import java.util.Comparator;
import java.util.List;

public class Population<T> {

    private static final String FAILED_TO_FIND_ANY_CHROMOSOME_THAT_FITS_MESSAGE = "Failed to find any chromosome that fits";

    protected List<Chromosome<T>> chromosomes;
    protected Chromosome<T> fittestChromosome;

    public Population(List<Chromosome<T>> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome<T> findFittestChromosome(FitnessTechnique<T> fitnessTechnique) throws GeneticAlgorithmException {
        fittestChromosome = chromosomes.stream()
                .max(Comparator.comparing(chromosome -> chromosome.calculateFitness(fitnessTechnique)))
                .orElseThrow(() -> new GeneticAlgorithmException(FAILED_TO_FIND_ANY_CHROMOSOME_THAT_FITS_MESSAGE));
        return fittestChromosome;
    }

    public List<Chromosome<T>> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(List<Chromosome<T>> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome<T> getFittestChromosome() {
        return fittestChromosome;
    }

}
