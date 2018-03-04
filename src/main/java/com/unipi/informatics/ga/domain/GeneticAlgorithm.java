package com.unipi.informatics.ga.domain;


import com.unipi.informatics.ga.techniques.CrossOverTechnique;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class GeneticAlgorithm{//} extends JComponent {

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
    private double startTime;
    private double duration;
    private List<Chromosome> fittestChromosomes;


    public GeneticAlgorithm(int populationCount, double mutationRate, FitnessTechnique fitnessTechnique, SelectionTechnique selectionTechnique, CrossOverTechnique crossOverTechnique, Map<Integer, MutationTechnique> mutationTechniqueMap) {
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
        fittestChromosomeEver = population.getFittestChromosome();
        while (bestFitnessEver < 1) {
            fittestChromosomes.add(fittestChromosomeEver);
            findFittestChromosomeEver();
            nextGeneration();
            draw();
        }
        this.duration = System.nanoTime() - startTime;
    }

    public abstract void initialGeneration();

    public abstract void findFittestChromosomeEver();

    public void nextGeneration() {
        List<Chromosome> nextChromosomes = new ArrayList<>();
        for (int i = 0; i < populationCount; i++) {
            Chromosome parentA = selectionTechnique.select(population);
            Chromosome parentB = selectionTechnique.select(population);
            Chromosome child = crossOverTechnique.crossOver(parentA, parentB);
            MutationTechnique mutationTechnique = getMutationTechniqueMap().get(new Random().nextInt(getMutationTechniqueMap().size()));
            child.mutate(mutationRate,mutationTechnique);
            nextChromosomes.add(child);
        }
        population = new Population(nextChromosomes);
        generationsCounter++;
    }

    public abstract void draw();

    public int getPopulationCount() {
        return populationCount;
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

    public void setPopulationCount(int populationCount) {
        this.populationCount = populationCount;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<Chromosome> getFittestChromosomes() {
        return fittestChromosomes;
    }

    public void setFittestChromosomes(List<Chromosome> fittestChromosomes) {
        this.fittestChromosomes = fittestChromosomes;
    }
}