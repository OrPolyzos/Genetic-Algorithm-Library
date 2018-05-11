package com.unipi.informatics.convex_hull;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.ch_ga.domain.CH_GeneticAlgorithm;
import com.unipi.informatics.convex_hull.ch_ga.techniques.crossover.CrossOverTechniqueElitism;
import com.unipi.informatics.convex_hull.ch_ga.techniques.fitness.FitnessTechniqueWithSickJoints;
import com.unipi.informatics.convex_hull.ch_ga.techniques.fitness.FitnessTechniqueWithoutSickJoints;
import com.unipi.informatics.convex_hull.ch_ga.techniques.mutation.MutationTechniqueAddOutsidePoint;
import com.unipi.informatics.convex_hull.ch_ga.techniques.mutation.MutationTechniqueRemoveIntersection;
import com.unipi.informatics.convex_hull.ch_ga.techniques.mutation.MutationTechniqueRemoveSickJoints;
import com.unipi.informatics.convex_hull.ch_ga.techniques.mutation.MutationTechniqueReplaceWithOutsidePoint;
import com.unipi.informatics.convex_hull.ch_ga.techniques.selection.SelectionTechniqueElitism;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import com.unipi.informatics.ga.techniques.CrossOverTechnique;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CH_Problem {

    private double mutationRate;
    private int populationCount;
    private List<Point> points;
    private Map<Integer, MutationTechnique<CH_Gene>> mutationTechniqueMap = new LinkedHashMap<>();
    private FitnessTechnique<CH_Gene> fitnessTechnique;
    private SelectionTechnique<CH_Gene> selectionTechnique;
    private CrossOverTechnique<CH_Gene> crossOverTechnique;

    public CH_Problem(int width, int height, double mutationRate, int populationCount, int pointsCount) {
        this.mutationRate = mutationRate;
        this.populationCount = populationCount;
        this.points = CH_Utilities.generatePoints(width, height, pointsCount);

    }

    private void setupForPhase1() {
        fitnessTechnique = FitnessTechniqueWithoutSickJoints.getInstance();
        selectionTechnique = SelectionTechniqueElitism.getInstance();
        crossOverTechnique = CrossOverTechniqueElitism.getInstance();

        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueAddOutsidePoint.getInstance());
        mutationTechniqueMap.put(1, MutationTechniqueReplaceWithOutsidePoint.getInstance());
        mutationTechniqueMap.put(2, MutationTechniqueRemoveIntersection.getInstance());
        mutationTechniqueMap.put(3, MutationTechniqueRemoveSickJoints.getInstance());
    }

    private void setupForPhase2() {
        fitnessTechnique = FitnessTechniqueWithSickJoints.getInstance();
    }

    public GeneticAlgorithm<CH_Gene> solve() {
        setupForPhase1();
        CH_GeneticAlgorithm chGeneticAlgorithm = new CH_GeneticAlgorithm(points, populationCount, mutationRate, fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        chGeneticAlgorithm.initialGeneration();
        chGeneticAlgorithm.run();

        setupForPhase2();
        chGeneticAlgorithm.setFitnessTechnique(fitnessTechnique);
        chGeneticAlgorithm.setMutationTechniqueMap(mutationTechniqueMap);
        chGeneticAlgorithm.eliteGeneration();
        chGeneticAlgorithm.run();
        return chGeneticAlgorithm;
    }
}
