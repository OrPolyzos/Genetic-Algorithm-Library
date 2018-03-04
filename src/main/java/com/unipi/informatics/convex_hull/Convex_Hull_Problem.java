package com.unipi.informatics.convex_hull;

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
import com.unipi.informatics.convex_hull.utilities.ConvexHullUtilities;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import com.unipi.informatics.ga.techniques.CrossOverTechnique;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Convex_Hull_Problem {

    private double mutationRate;
    private int populationCount;
    private int pointsCount;
    private List<Point> points;
    private Map<Integer, MutationTechnique> mutationTechniqueMap = new LinkedHashMap<>();
    private FitnessTechnique fitnessTechnique;
    private SelectionTechnique selectionTechnique;
    private CrossOverTechnique crossOverTechnique;

    public Convex_Hull_Problem(double mutationRate, int populationCount, int pointsCount) {
        this.mutationRate = mutationRate;
        this.populationCount = populationCount;
        this.pointsCount = pointsCount;
        this.points = ConvexHullUtilities.generatePoints(pointsCount);

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

        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueRemoveSickJoints.getInstance());

        System.out.println(fitnessTechnique.toString());
        System.out.println(selectionTechnique.toString());
        System.out.println(crossOverTechnique.toString());
        for (Integer key : mutationTechniqueMap.keySet()) {
            System.out.println(mutationTechniqueMap.get(key).toString());
        }
    }

    public GeneticAlgorithm solve() {
        setupForPhase1();
        CH_GeneticAlgorithm chGeneticAlgorithm = new CH_GeneticAlgorithm(points, populationCount, mutationRate, fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        chGeneticAlgorithm.initialGeneration();
        chGeneticAlgorithm.run();

        if (chGeneticAlgorithm.getFittestChromosomeEver().getDNA().getGene().get(3).size() == 0) {
            System.out.println("Finished!");
            return chGeneticAlgorithm;
        }
        setupForPhase2();
        chGeneticAlgorithm.setFitnessTechnique(fitnessTechnique);
        chGeneticAlgorithm.setMutationTechniqueMap(mutationTechniqueMap);
        chGeneticAlgorithm.eliteGeneration();
        chGeneticAlgorithm.run();
        return chGeneticAlgorithm;
    }
}
