package com.algorithm.genetic.library.ga.domain;

import com.algorithm.genetic.library.ga.techniques.CrossOverTechnique;
import com.algorithm.genetic.library.ga.techniques.FitnessTechnique;
import com.algorithm.genetic.library.ga.techniques.MutationTechnique;
import com.algorithm.genetic.library.ga.techniques.SelectionTechnique;

import java.time.Duration;
import java.time.LocalTime;
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
    private LocalTime startTime;
    private Duration duration;

    public GeneticAlgorithm(int populationCount, double mutationRate, FitnessTechnique<T> fitnessTechnique, SelectionTechnique<T> selectionTechnique, CrossOverTechnique<T> crossOverTechnique, Map<Integer, MutationTechnique<T>> mutationTechniqueMap) {
        this.populationCount = populationCount;
        this.mutationRate = mutationRate;
        this.fitnessTechnique = fitnessTechnique;
        this.selectionTechnique = selectionTechnique;
        this.crossOverTechnique = crossOverTechnique;
        this.mutationTechniqueMap = mutationTechniqueMap;
        this.startTime = LocalTime.now();
        this.duration = Duration.between(startTime, LocalTime.now());
        this.fittestChromosomes = new ArrayList<>();
    }

    public void run() {
        bestFitnessEver = Double.MIN_VALUE;
        while (bestFitnessEver < 1) {
            findFittestChromosomeEver();
            fittestChromosomes.add(population.getFittestChromosome());
            draw();
            nextGeneration();
            this.duration = Duration.between(startTime, LocalTime.now());
        }
        this.duration = Duration.between(startTime, LocalTime.now());
    }
    public void draw(){}

    public abstract void initialGeneration();

    public void findFittestChromosomeEver() {
        if (population.getFittestChromosome().getFitness() > bestFitnessEver) {
            fittestChromosomeEver = population.getFittestChromosome();
            bestFitnessEver = fittestChromosomeEver.getFitness();
        }
    }

    public void nextGeneration() {
        List<Chromosome<T>> nextChromosomes = new ArrayList<>();
        for (int i = 0; i < populationCount; i++) {
            Chromosome<T> parentA = selectionTechnique.select(population);
            Chromosome<T> parentB = selectionTechnique.select(population);
            Chromosome<T> child = crossOverTechnique.crossOver(parentA, parentB);
            MutationTechnique<T> mutationTechnique = getMutationTechniqueMap().get(new Random().nextInt(getMutationTechniqueMap().size()));
            child.mutate(mutationRate, mutationTechnique);
            nextChromosomes.add(child);
        }
        population.setChromosomes(nextChromosomes);
        generationsCounter++;
    }

    public Population<T> getPopulation() {
        return population;
    }

    public void setPopulation(Population<T> population) {
        this.population = population;
    }

    public Chromosome<T> getFittestChromosomeEver() {
        return fittestChromosomeEver;
    }

    public void setFittestChromosomeEver(Chromosome<T> fittestChromosomeEver) {
        this.fittestChromosomeEver = fittestChromosomeEver;
    }

    public List<Chromosome<T>> getFittestChromosomes() {
        return fittestChromosomes;
    }

    public void setFittestChromosomes(List<Chromosome<T>> fittestChromosomes) {
        this.fittestChromosomes = fittestChromosomes;
    }

    public int getPopulationCount() {
        return populationCount;
    }

    public void setPopulationCount(int populationCount) {
        this.populationCount = populationCount;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public FitnessTechnique<T> getFitnessTechnique() {
        return fitnessTechnique;
    }

    public void setFitnessTechnique(FitnessTechnique<T> fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;
    }

    public SelectionTechnique<T> getSelectionTechnique() {
        return selectionTechnique;
    }

    public void setSelectionTechnique(SelectionTechnique<T> selectionTechnique) {
        this.selectionTechnique = selectionTechnique;
    }

    public CrossOverTechnique<T> getCrossOverTechnique() {
        return crossOverTechnique;
    }

    public void setCrossOverTechnique(CrossOverTechnique<T> crossOverTechnique) {
        this.crossOverTechnique = crossOverTechnique;
    }

    public Map<Integer, MutationTechnique<T>> getMutationTechniqueMap() {
        return mutationTechniqueMap;
    }

    public void setMutationTechniqueMap(Map<Integer, MutationTechnique<T>> mutationTechniqueMap) {
        this.mutationTechniqueMap = mutationTechniqueMap;
    }

    public double getBestFitnessEver() {
        return bestFitnessEver;
    }

    public void setBestFitnessEver(double bestFitnessEver) {
        this.bestFitnessEver = bestFitnessEver;
    }

    public int getGenerationsCounter() {
        return generationsCounter;
    }

    public void setGenerationsCounter(int generationsCounter) {
        this.generationsCounter = generationsCounter;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
