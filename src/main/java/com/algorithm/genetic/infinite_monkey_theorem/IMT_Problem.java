package com.algorithm.genetic.infinite_monkey_theorem;


import com.algorithm.genetic.ga.domain.GeneticAlgorithm;
import com.algorithm.genetic.ga.techniques.CrossOverTechnique;
import com.algorithm.genetic.ga.techniques.FitnessTechnique;
import com.algorithm.genetic.ga.techniques.MutationTechnique;
import com.algorithm.genetic.ga.techniques.SelectionTechnique;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_Gene;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_GeneticAlgorithm;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.crossover.CrossOverTechniqueHalfWay;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.fitness.FitnessTechniqueForEachLetter;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.mutation.MutationTechniqueReplaceWithRandomLetterFromKeyboard;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.selection.SelectionTechniqueRouletteWheel;

import java.util.LinkedHashMap;
import java.util.Map;

public class IMT_Problem {
    private double mutationRate;
    private int populationCount;
    private String targetPhrase;
    private Map<Integer, MutationTechnique<IMT_Gene>> mutationTechniqueMap = new LinkedHashMap<>();
    private FitnessTechnique<IMT_Gene> fitnessTechnique;
    private SelectionTechnique<IMT_Gene> selectionTechnique;
    private CrossOverTechnique<IMT_Gene> crossOverTechnique;

    public IMT_Problem(/*int width, int height, */String targetPhrase, double mutationRate, int populationCount/*, int pointsCount*/) {
        this.targetPhrase = targetPhrase;
        this.mutationRate = mutationRate;
        this.populationCount = populationCount;
    }

    private void setup() {
        fitnessTechnique = FitnessTechniqueForEachLetter.getInstance();
        selectionTechnique = SelectionTechniqueRouletteWheel.getInstance();
        crossOverTechnique = CrossOverTechniqueHalfWay.getInstance();
        mutationTechniqueMap.put(0, MutationTechniqueReplaceWithRandomLetterFromKeyboard.getInstance());
    }

    public GeneticAlgorithm<IMT_Gene> solve() {
        setup();
        IMT_GeneticAlgorithm chGeneticAlgorithm = new IMT_GeneticAlgorithm(targetPhrase, populationCount, mutationRate, fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        chGeneticAlgorithm.initialGeneration();
        chGeneticAlgorithm.run();
        return chGeneticAlgorithm;
    }
}
