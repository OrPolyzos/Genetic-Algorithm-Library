package com.algorithms.ai.domain;

import com.algorithms.ai.exception.GeneticAlgorithmException;
import com.algorithms.ai.provider.CrossOverTechniqueProvider;
import com.algorithms.ai.provider.FitnessTechniqueProvider;
import com.algorithms.ai.provider.InitialGenerationProvider;
import com.algorithms.ai.provider.MutationTechniqueProvider;
import com.algorithms.ai.provider.SelectionTechniqueProvider;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm<T> {

    private static final String FAILED_TO_FIND_ANY_CHROMOSOME_THAT_FITS_MESSAGE = "Failed to find any chromosome that fits";

    protected FitnessTechniqueProvider<T> fitnessTechniqueProvider;
    protected SelectionTechniqueProvider<T> selectionTechniqueProvider;
    protected CrossOverTechniqueProvider<T> crossOverTechniqueProvider;
    protected MutationTechniqueProvider<T> mutationTechniqueProvider;

    protected double mutationRate;
    protected List<Chromosome<T>> population;

    protected Chromosome<T> fittestChromosomeEver;
    protected Chromosome<T> fittestChromosome;

    private int generationsCounter;

    public GeneticAlgorithm(double mutationRate,
                            InitialGenerationProvider<T> initialGenerationProvider,
                            FitnessTechniqueProvider<T> fitnessTechniqueProvider,
                            SelectionTechniqueProvider<T> selectionTechniqueProvider,
                            CrossOverTechniqueProvider<T> crossOverTechniqueProvider,
                            MutationTechniqueProvider<T> mutationTechniqueProvider) {
        this.population = initialGenerationProvider.provideInitialGeneration();
        this.mutationRate = mutationRate;
        this.fitnessTechniqueProvider = fitnessTechniqueProvider;
        this.selectionTechniqueProvider = selectionTechniqueProvider;
        this.crossOverTechniqueProvider = crossOverTechniqueProvider;
        this.mutationTechniqueProvider = mutationTechniqueProvider;
    }

    public void run() throws GeneticAlgorithmException {
        while (fittestChromosomeEver == null || shouldContinue()) {
            findFittestChromosomeEver();
            drawCurrentState();
            formNextGeneration();
        }
    }


    protected void drawCurrentState() {
        System.out.println(String.format("Generation: %s, Fitness: %s", generationsCounter, String.valueOf(fittestChromosomeEver.getFitness())));
    }

    protected boolean shouldContinue() {
        return fittestChromosomeEver == null || fittestChromosomeEver.getFitness() < 1;
    }

    protected void findFittestChromosomeEver() throws GeneticAlgorithmException {
        fittestChromosome = findPopulationsFittestChromosome();
        if (fittestChromosomeEver == null || fittestChromosomeEver.getFitness() < fittestChromosome.getFitness()) {
            fittestChromosomeEver = fittestChromosome;
        }
    }

    public Chromosome<T> findPopulationsFittestChromosome() throws GeneticAlgorithmException {
        return population.stream()
                .peek(chromosome -> chromosome.setFitness(fitnessTechniqueProvider.provideFitnessTechnique(chromosome)))
                .max(Comparator.comparingDouble(Chromosome::getFitness))
                .orElseThrow(() -> new GeneticAlgorithmException(FAILED_TO_FIND_ANY_CHROMOSOME_THAT_FITS_MESSAGE));
    }

    protected void formNextGeneration() {
        population = IntStream.range(0, population.size())
                .boxed()
                .map(i -> {
                    Chromosome<T> firstParent = selectionTechniqueProvider.provideSelectionTechnique(population, fittestChromosomeEver, fittestChromosome);
                    Chromosome<T> secondParent = selectionTechniqueProvider.provideSelectionTechnique(population, fittestChromosomeEver, fittestChromosome);
                    Chromosome<T> child = crossOverTechniqueProvider.provideCrossOverTechnique(firstParent, secondParent);
                    mutationTechniqueProvider.provideMutationTechnique(child, mutationRate);
                    return child;
                })
                .collect(Collectors.toList());
        generationsCounter++;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public List<Chromosome<T>> getPopulation() {
        return population;
    }

    public Chromosome<T> getFittestChromosomeEver() {
        return fittestChromosomeEver;
    }

    public int getGenerationsCounter() {
        return generationsCounter;
    }

    public Chromosome<T> getFittestChromosome() {
        return fittestChromosome;
    }
}
