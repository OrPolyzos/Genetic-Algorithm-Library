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

/**
 * The type Genetic algorithm.
 *
 * @param <T> the type parameter
 */
public class GeneticAlgorithm<T> {

    private static final String FAILED_TO_FIND_ANY_CHROMOSOME_THAT_FITS_MESSAGE = "Failed to find any chromosome that fits";

    /**
     * The Fitness technique provider.
     */
    protected FitnessTechniqueProvider<T> fitnessTechniqueProvider;
    /**
     * The Selection technique provider.
     */
    protected SelectionTechniqueProvider<T> selectionTechniqueProvider;
    /**
     * The Cross over technique provider.
     */
    protected CrossOverTechniqueProvider<T> crossOverTechniqueProvider;
    /**
     * The Mutation technique provider.
     */
    protected MutationTechniqueProvider<T> mutationTechniqueProvider;

    /**
     * The Mutation rate.
     */
    protected double mutationRate;
    /**
     * The Population.
     */
    protected List<Chromosome<T>> population;

    /**
     * The Fittest chromosome ever.
     */
    protected Chromosome<T> fittestChromosomeEver;
    /**
     * The Fittest chromosome.
     */
    protected Chromosome<T> fittestChromosome;

    private int generationsCounter;

    /**
     * Instantiates a new Genetic algorithm.
     *
     * @param mutationRate               the mutation rate
     * @param initialGenerationProvider  the initial generation provider
     * @param fitnessTechniqueProvider   the fitness technique provider
     * @param selectionTechniqueProvider the selection technique provider
     * @param crossOverTechniqueProvider the cross over technique provider
     * @param mutationTechniqueProvider  the mutation technique provider
     */
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

    /**
     * Run. The method to call, so the algorithm starts
     *
     * @throws GeneticAlgorithmException the genetic algorithm exception
     */
    public void run() throws GeneticAlgorithmException {
        while (fittestChromosomeEver == null || shouldContinue()) {
            findFittestChromosomeEver();
            drawCurrentState();
            formNextGeneration();
        }
    }

    /**
     * Draw current state.
     */
    protected void drawCurrentState() {
        System.out.println(String.format("Generation: %s, Fitness: %s", generationsCounter, String.valueOf(fittestChromosomeEver.getFitness())));
    }

    /**
     * The condition that will indicate the genetic algorithm should continue running.
     *
     * @return false if the algorithm has come to a solution
     */
    protected boolean shouldContinue() {
        return fittestChromosomeEver == null || fittestChromosomeEver.getFitness() < 1;
    }

    /**
     * Find the fittest chromosome ever.
     *
     * @throws GeneticAlgorithmException
     */
    protected void findFittestChromosomeEver() throws GeneticAlgorithmException {
        fittestChromosome = findPopulationsFittestChromosome();
        if (fittestChromosomeEver == null || fittestChromosomeEver.getFitness() < fittestChromosome.getFitness()) {
            fittestChromosomeEver = fittestChromosome;
        }
    }

    /**
     * Find the current population's fittest chromosome.
     *
     * @return the current population's fittest chromosome
     * @throws GeneticAlgorithmException
     */
    public Chromosome<T> findPopulationsFittestChromosome() throws GeneticAlgorithmException {
        return population.stream()
                .peek(chromosome -> chromosome.setFitness(fitnessTechniqueProvider.provideFitnessTechnique(chromosome)))
                .max(Comparator.comparingDouble(Chromosome::getFitness))
                .orElseThrow(() -> new GeneticAlgorithmException(FAILED_TO_FIND_ANY_CHROMOSOME_THAT_FITS_MESSAGE));
    }

    /**
     * Form the next generation.
     */
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

    /**
     * Gets mutation rate.
     *
     * @return the mutation rate
     */
    public double getMutationRate() {
        return mutationRate;
    }

    /**
     * Gets current population.
     *
     * @return the current population
     */
    public List<Chromosome<T>> getPopulation() {
        return population;
    }

    /**
     * Gets fittest chromosome ever.
     *
     * @return the fittest chromosome ever
     */
    public Chromosome<T> getFittestChromosomeEver() {
        return fittestChromosomeEver;
    }

    /**
     * Gets the current population's fittest chromosome.
     *
     * @return the the current population's fittest chromosome
     */
    public Chromosome<T> getFittestChromosome() {
        return fittestChromosome;
    }

    /**
     * Gets generations counter.
     *
     * @return the generations counter
     */
    public int getGenerationsCounter() {
        return generationsCounter;
    }

}
