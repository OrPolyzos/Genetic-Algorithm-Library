package ga.domain;


import ga.techniques.CrossOverTechnique;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;
import ga.techniques.SelectionTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GeneticAlgorithm {

    private int populationCount;
    private double mutationRate;

    private FitnessTechnique fitnessTechnique;
    private SelectionTechnique selectionTechnique;
    private CrossOverTechnique crossOverTechnique;
    private Map<Integer, MutationTechnique> mutationTechniqueMap;

    private Population population;
    private Chromosome fittestChromosomeEver;
    private double bestFitnessEver;
    private int generationsCounter;

    public GeneticAlgorithm(int populationCount, double mutationRate, FitnessTechnique fitnessTechnique, SelectionTechnique selectionTechnique, Map<Integer, MutationTechnique> mutationTechniqueMap) {
        this.populationCount = populationCount;
        this.mutationRate = mutationRate;
        this.fitnessTechnique = fitnessTechnique;
        this.selectionTechnique = selectionTechnique;
        this.mutationTechniqueMap = mutationTechniqueMap;
    }

    public void run() {
        bestFitnessEver = Double.MIN_VALUE;
        fittestChromosomeEver = population.getFittestChromosome();
        while (bestFitnessEver < 1) {
            draw();
            findFittestChromosomeEver();
            nextGeneration();
        }
        draw();
    }

    public abstract void initialGeneration();

    public abstract void findFittestChromosomeEver();

    public void nextGeneration() {
        List<Chromosome> nextChromosomes = new ArrayList<>();
        for (int i = 0; i < populationCount; i++) {
            Chromosome parentA = selectionTechnique.select(population);
            Chromosome parentB = selectionTechnique.select(population);
            Chromosome child = crossOverTechnique.crossOver(parentA, parentB);
            child.mutate(mutationRate);
            nextChromosomes.add(child);
        }
        population = new Population(nextChromosomes, this);
        generationsCounter++;
    }

    public abstract void draw();

    public int getPopulationCount() {
        return populationCount;
    }

    public void setPopulationCount(int populationCount) {
        this.populationCount = populationCount;
    }

    public CrossOverTechnique getCrossOverTechnique() {
        return crossOverTechnique;
    }

    public void setCrossOverTechnique(CrossOverTechnique crossOverTechnique) {
        this.crossOverTechnique = crossOverTechnique;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public FitnessTechnique getFitnessTechnique() {
        return fitnessTechnique;
    }

    public void setFitnessTechnique(FitnessTechnique fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;
    }

    public SelectionTechnique getSelectionTechnique() {
        return selectionTechnique;
    }

    public void setSelectionTechnique(SelectionTechnique selectionTechnique) {
        this.selectionTechnique = selectionTechnique;
    }

    public Map<Integer, MutationTechnique> getMutationTechniqueMap() {
        return mutationTechniqueMap;
    }

    public void setMutationTechniqueMap(Map<Integer, MutationTechnique> mutationTechniqueMap) {
        this.mutationTechniqueMap = mutationTechniqueMap;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public Chromosome getFittestChromosomeEver() {
        return fittestChromosomeEver;
    }

    public void setFittestChromosomeEver(Chromosome fittestChromosomeEver) {
        this.fittestChromosomeEver = fittestChromosomeEver;
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
}