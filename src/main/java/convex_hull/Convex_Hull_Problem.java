package convex_hull;

import convex_hull.ch_ga.domain.CH_GeneticAlgorithm;
import convex_hull.ch_ga.techniques.crossover.CrossOverTechniqueElitism;
import convex_hull.ch_ga.techniques.fitness.FitnessTechniqueWithSickJoints;
import convex_hull.ch_ga.techniques.fitness.FitnessTechniqueWithoutSickJoints;
import convex_hull.ch_ga.techniques.mutation.MutationTechniqueAddOutsidePoint;
import convex_hull.ch_ga.techniques.mutation.MutationTechniqueRemoveIntersection;
import convex_hull.ch_ga.techniques.mutation.MutationTechniqueRemoveSickJoints;
import convex_hull.ch_ga.techniques.mutation.MutationTechniqueReplaceWithOutsidePoint;
import convex_hull.ch_ga.techniques.selection.SelectionTechniqueElitism;
import convex_hull.domain.Point;
import convex_hull.utilities.ConvexHullUtilities;
import convex_hull.utilities.TimeIt;
import ga.techniques.CrossOverTechnique;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;
import ga.techniques.SelectionTechnique;
import view.Canvas;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Convex_Hull_Problem {

    private static double mutationRate;
    private static int populationCount;
    private static int pointsCount;

    private static Map<Integer, MutationTechnique> mutationTechniqueMap = new LinkedHashMap<>();
    private static FitnessTechnique fitnessTechnique;
    private static SelectionTechnique selectionTechnique;
    private static CrossOverTechnique crossOverTechnique;

//    private static Canvas canvas;

    public Convex_Hull_Problem() {
//        canvas = Canvas.getInstance();
        mutationRate = 0.03;//Canvas.getInstance().getCh_jInputPanel().getMutationRate();
        populationCount = 500;//Canvas.getInstance().getCh_jInputPanel().getPopulationCount();
        pointsCount = 1000;//Canvas.getInstance().getCh_jInputPanel().getPointsCount();
    }

    private static void setupForPhase(){
        fitnessTechnique = FitnessTechniqueWithSickJoints.getInstance();
        selectionTechnique = SelectionTechniqueElitism.getInstance();
        crossOverTechnique = CrossOverTechniqueElitism.getInstance();

        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueAddOutsidePoint.getInstance());
        mutationTechniqueMap.put(1, MutationTechniqueReplaceWithOutsidePoint.getInstance());
        mutationTechniqueMap.put(2, MutationTechniqueRemoveIntersection.getInstance());
        mutationTechniqueMap.put(3, MutationTechniqueRemoveSickJoints.getInstance());
    }
    private static void setupForPhase1() {
        fitnessTechnique = FitnessTechniqueWithoutSickJoints.getInstance();
        selectionTechnique = SelectionTechniqueElitism.getInstance();
        crossOverTechnique = CrossOverTechniqueElitism.getInstance();

        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueAddOutsidePoint.getInstance());
        mutationTechniqueMap.put(1, MutationTechniqueReplaceWithOutsidePoint.getInstance());
        mutationTechniqueMap.put(2, MutationTechniqueRemoveIntersection.getInstance());
        mutationTechniqueMap.put(3, MutationTechniqueRemoveSickJoints.getInstance());

        System.out.println("\nPreparing for Phase 1");
        System.out.println(fitnessTechnique.toString());
        System.out.println(selectionTechnique.toString());
        System.out.println(crossOverTechnique.toString());
        for (Integer key : mutationTechniqueMap.keySet()){
            System.out.println(mutationTechniqueMap.get(key).toString());
        }
    }

    private static void setupForPhase2() {
        fitnessTechnique = FitnessTechniqueWithSickJoints.getInstance();

        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueRemoveSickJoints.getInstance());

        System.out.println("\nPreparing for Phase 2");
        System.out.println(fitnessTechnique.toString());
        System.out.println(selectionTechnique.toString());
        System.out.println(crossOverTechnique.toString());
        for (Integer key : mutationTechniqueMap.keySet()){
            System.out.println(mutationTechniqueMap.get(key).toString());
        }
    }

    public void initializeProblem() {
        List<Point> points = ConvexHullUtilities.generatePoints(pointsCount);//, canvas);
//        TimeIt.code(() -> solve(points));
        solve(points);
    }

    public void solve(List<Point> points) {
//        setupForPhase();
//        CH_GeneticAlgorithm ch_geneticAlgorithm = new CH_GeneticAlgorithm(points,populationCount,mutationRate,fitnessTechnique,selectionTechnique,mutationTechniqueMap);
//        ch_geneticAlgorithm.setCrossOverTechnique(crossOverTechnique);
//        ch_geneticAlgorithm.initialGeneration();
//        ch_geneticAlgorithm.run();

        setupForPhase1();
        CH_GeneticAlgorithm chGeneticAlgorithm = new CH_GeneticAlgorithm(points, populationCount, mutationRate, fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        chGeneticAlgorithm.initialGeneration();
        chGeneticAlgorithm.run();

        if (chGeneticAlgorithm.getFittestChromosomeEver().getDNA().getGene().get(3).size() == 0){
            System.out.println("End");
            return;
        }
        setupForPhase2();
        chGeneticAlgorithm.setFitnessTechnique(fitnessTechnique);
        chGeneticAlgorithm.setMutationTechniqueMap(mutationTechniqueMap);
        chGeneticAlgorithm.eliteGeneration();
        chGeneticAlgorithm.run();
    }
}
