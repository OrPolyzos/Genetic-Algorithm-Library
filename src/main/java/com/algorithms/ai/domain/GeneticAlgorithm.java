package com.algorithms.ai.domain;

import com.algorithms.ai.exception.GeneticAlgorithmException;
import com.algorithms.ai.provider.CrossOverTechniqueProvider;
import com.algorithms.ai.provider.FitnessTechniqueProvider;
import com.algorithms.ai.provider.MutationTechniqueProvider;
import com.algorithms.ai.provider.SelectionTechniqueProvider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class GeneticAlgorithm<T> {

    protected FitnessTechniqueProvider<T> fitnessTechniqueProvider;
    protected SelectionTechniqueProvider<T> selectionTechniqueProvider;
    protected CrossOverTechniqueProvider<T> crossOverTechniqueProvider;
    protected MutationTechniqueProvider<T> mutationTechniqueProvider;

    protected int populationCount;
    protected double mutationRate;

    protected Population<T> population;
    protected Chromosome<T> fittestChromosomeEver;

    private int generationsCounter;

    public GeneticAlgorithm(int populationCount, double mutationRate,
                            FitnessTechniqueProvider<T> fitnessTechniqueProvider,
                            SelectionTechniqueProvider<T> selectionTechniqueProvider,
                            CrossOverTechniqueProvider<T> crossOverTechniqueProvider,
                            MutationTechniqueProvider<T> mutationTechniqueProvider) {
        this.populationCount = populationCount;
        this.mutationRate = mutationRate;
        this.fitnessTechniqueProvider = fitnessTechniqueProvider;
        this.selectionTechniqueProvider = selectionTechniqueProvider;
        this.crossOverTechniqueProvider = crossOverTechniqueProvider;
        this.mutationTechniqueProvider = mutationTechniqueProvider;
    }

    public void run() throws GeneticAlgorithmException {
        initialGeneration();
        while (fittestChromosomeEver == null || shouldContinue()) {
            findFittestChromosomeEver();
            drawCurrentState();
            formNextGeneration();
        }
    }

    protected abstract void initialGeneration();

    protected void drawCurrentState() {
        System.out.println(String.format("Generation: %s, Fitness: %s", generationsCounter, String.valueOf(fittestChromosomeEver.getFitness())));
    }

    protected boolean shouldContinue() {
        return fittestChromosomeEver == null || fittestChromosomeEver.getFitness() < 1;
    }

    protected void findFittestChromosomeEver() throws GeneticAlgorithmException {
        Chromosome<T> populationFittest = population.findFittestChromosome(fitnessTechniqueProvider.provideFitnessTechnique());
        if (fittestChromosomeEver == null || fittestChromosomeEver.getFitness() < populationFittest.getFitness()) {
            fittestChromosomeEver = populationFittest;
        }
    }

    protected void formNextGeneration() {
        List<Chromosome<T>> nextGeneration = IntStream.range(0, populationCount)
                .boxed()
                .map(i -> {
                    Chromosome<T> firstParent = selectionTechniqueProvider.provideSelectionTechnique().select(population);
                    Chromosome<T> secondParent = selectionTechniqueProvider.provideSelectionTechnique().select(population);
                    Chromosome<T> child = crossOverTechniqueProvider.provideCrossOverTechnique().crossOver(firstParent, secondParent);
                    child.mutate(mutationRate, mutationTechniqueProvider.provideMutationTechnique());
                    return child;
                })
                .collect(Collectors.toList());
        population.setChromosomes(nextGeneration);
        generationsCounter++;
    }

    public int getPopulationCount() {
        return populationCount;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public Population<T> getPopulation() {
        return population;
    }

    public Chromosome<T> getFittestChromosomeEver() {
        return fittestChromosomeEver;
    }

    public int getGenerationsCounter() {
        return generationsCounter;
    }
}
