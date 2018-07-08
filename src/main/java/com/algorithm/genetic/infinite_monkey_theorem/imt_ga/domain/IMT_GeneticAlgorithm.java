package com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain;

import com.algorithm.genetic.ga.domain.Chromosome;
import com.algorithm.genetic.ga.domain.Dna;
import com.algorithm.genetic.ga.domain.GeneticAlgorithm;
import com.algorithm.genetic.ga.domain.Population;
import com.algorithm.genetic.ga.techniques.CrossOverTechnique;
import com.algorithm.genetic.ga.techniques.FitnessTechnique;
import com.algorithm.genetic.ga.techniques.MutationTechnique;
import com.algorithm.genetic.ga.techniques.SelectionTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMT_GeneticAlgorithm extends GeneticAlgorithm<IMT_Gene> {

    private String targetPhrase;

    public IMT_GeneticAlgorithm(String targetPhrase, int populationCount, double mutationRate, FitnessTechnique<IMT_Gene> ch_fitnessTechnique, SelectionTechnique<IMT_Gene> selectionTechnique, CrossOverTechnique<IMT_Gene> crossOverTechnique, Map<Integer, MutationTechnique<IMT_Gene>> mutationTechniqueMap) {
        super(populationCount, mutationRate, ch_fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        this.targetPhrase = targetPhrase;
    }

    @Override
    public void initialGeneration() {
        List<Chromosome<IMT_Gene>> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            IMT_Gene gene = new IMT_Gene(targetPhrase, IMT_Keyboard.getRandomPhrase(targetPhrase.length()));
            Dna<IMT_Gene> ch_dna = new Dna<>(gene);
            Chromosome<IMT_Gene> chromosome = new Chromosome<>(ch_dna, getFitnessTechnique());
            chromosomes.add(chromosome);
        }
        Population<IMT_Gene> initialPopulation = new Population<>(chromosomes);
        this.setPopulation(initialPopulation);
    }

    @Override
    public void draw() {
        System.out.println(getFittestChromosomeEver().getDna().getGene().getOwnPhrase() + " " + getFittestChromosomeEver().getFitness());
    }

    @Override
    public String toString() {
        return "Generation: " + getGenerationsCounter() + "\n" + "Best Phrase Ever: " + getFittestChromosomeEver().getDna().getGene();
    }
}