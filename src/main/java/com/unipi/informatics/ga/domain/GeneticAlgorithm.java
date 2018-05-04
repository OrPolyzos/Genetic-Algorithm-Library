package com.unipi.informatics.ga.domain;

import com.unipi.informatics.ga.techniques.CrossOverTechnique;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class GeneticAlgorithm<T> {

    private Population<T> population;
    private Chromosome<T> fittestChromosomeEver;
    private List<Chromosome<T>> fittestChromosomes;
    private int populationCount;
    private double mutationRate;
    private FitnessTechnique<T> fitnessTechnique;
    private SelectionTechnique<T> selectionTechnique;
    private CrossOverTechnique<T> crossOverTechnique;
    private Map<Integer, MutationTechnique<T>> mutationTechniqueMap;
    private double bestFitnessEver;
    private int generationsCounter;
    private double startTime;
    private double duration;

    public GeneticAlgorithm(int populationCount, double mutationRate, FitnessTechnique<T> fitnessTechnique, SelectionTechnique<T> selectionTechnique, CrossOverTechnique<T> crossOverTechnique, Map<Integer, MutationTechnique<T>> mutationTechniqueMap) {
        this.populationCount = populationCount;
        this.mutationRate = mutationRate;
        this.fitnessTechnique = fitnessTechnique;
        this.selectionTechnique = selectionTechnique;
        this.crossOverTechnique = crossOverTechnique;
        this.mutationTechniqueMap = mutationTechniqueMap;
        this.startTime = System.nanoTime();
        this.duration = System.nanoTime() - startTime;
        this.fittestChromosomes = new ArrayList<>();
    }

    public void run() {
        bestFitnessEver = Double.MIN_VALUE;
        while (bestFitnessEver < 1) {
            findFittestChromosomeEver();
            fittestChromosomes.add(fittestChromosomeEver);
            nextGeneration();
        }
        this.duration = System.nanoTime() - startTime;
    }

    public abstract void initialGeneration();

    public void findFittestChromosomeEver() {
        if (population.getFittestChromosome().getFitness() > bestFitnessEver) {
            fittestChromosomeEver = population.getFittestChromosome();
            bestFitnessEver = fittestChromosomeEver.getFitness();
        }
    }

    private void nextGeneration() {
        List<Chromosome<T>> nextChromosomes = new ArrayList<>();
        for (int i = 0; i < populationCount; i++) {
            Chromosome<T> parentA = selectionTechnique.select(population);
            Chromosome<T> parentB = selectionTechnique.select(population);
            Chromosome<T> child = crossOverTechnique.crossOver(parentA, parentB);
            MutationTechnique<T> mutationTechnique = getMutationTechniqueMap().get(new Random().nextInt(getMutationTechniqueMap().size()));
            child = child.mutate(mutationRate, mutationTechnique);
            nextChromosomes.add(child);
        }
        population = new Population<>(nextChromosomes);
        generationsCounter++;
    }

    public int getPopulationCount() {
        return populationCount;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    protected FitnessTechnique<T> getFitnessTechnique() {
        return fitnessTechnique;
    }

    public void setFitnessTechnique(FitnessTechnique<T> fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;
    }

    private Map<Integer, MutationTechnique<T>> getMutationTechniqueMap() {
        return mutationTechniqueMap;
    }

    public void setMutationTechniqueMap(Map<Integer, MutationTechnique<T>> mutationTechniqueMap) {
        this.mutationTechniqueMap = mutationTechniqueMap;
    }

    protected Population<T> getPopulation() {
        return population;
    }

    protected void setPopulation(Population<T> population) {
        this.population = population;
    }

    public Chromosome<T> getFittestChromosomeEver() {
        return fittestChromosomeEver;
    }

    protected void setFittestChromosomeEver(Chromosome<T> fittestChromosomeEver) {
        this.fittestChromosomeEver = fittestChromosomeEver;
    }

    protected double getBestFitnessEver() {
        return bestFitnessEver;
    }

    protected void setBestFitnessEver(double bestFitnessEver) {
        this.bestFitnessEver = bestFitnessEver;
    }

    public int getGenerationsCounter() {
        return generationsCounter;
    }

    public double getDuration() {
        return duration;
    }

    public List<Chromosome<T>> getFittestChromosomes() {
        return fittestChromosomes;
    }
}
