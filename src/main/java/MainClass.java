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
import ga.techniques.CrossOverTechnique;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;
import ga.techniques.SelectionTechnique;
import view.Canvas;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainClass {

    private static double mutationRate = 1;
    private static int populationCount = 50;
    private static int pointsCount = 500;

    private static Map<Integer, MutationTechnique> mutationTechniqueMap = new LinkedHashMap<>();
    private static FitnessTechnique fitnessTechnique;
    private static SelectionTechnique selectionTechnique;
    private static CrossOverTechnique crossOverTechnique;

    public static void main(String[] args) {
        while (true) {
            Canvas canvas = new Canvas(800, 720);
            List<Point> points = ConvexHullUtilities.generatePoints(pointsCount, canvas);
            setupForPhase1();
            CH_GeneticAlgorithm chGeneticAlgorithm = new CH_GeneticAlgorithm(points, canvas, populationCount, mutationRate, fitnessTechnique, selectionTechnique, mutationTechniqueMap);
            chGeneticAlgorithm.setCrossOverTechnique(crossOverTechnique);
            chGeneticAlgorithm.initialGeneration();
            chGeneticAlgorithm.run();

            setupForPhase2();
            chGeneticAlgorithm.setFitnessTechnique(fitnessTechnique);
            chGeneticAlgorithm.setMutationTechniqueMap(mutationTechniqueMap);
            chGeneticAlgorithm.eliteGeneration();
            chGeneticAlgorithm.run();
//            new Scanner(System.in).next();
        }
    }

    public static void setupForPhase1() {
        fitnessTechnique = FitnessTechniqueWithoutSickJoints.getInstance();
        selectionTechnique = SelectionTechniqueElitism.getInstance();
        crossOverTechnique = CrossOverTechniqueElitism.getInstance();

        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueAddOutsidePoint.getInstance());
        mutationTechniqueMap.put(1, MutationTechniqueReplaceWithOutsidePoint.getInstance());
        mutationTechniqueMap.put(2, MutationTechniqueRemoveIntersection.getInstance());
        mutationTechniqueMap.put(3, MutationTechniqueRemoveSickJoints.getInstance());
    }

    public static void setupForPhase2() {
        fitnessTechnique = FitnessTechniqueWithSickJoints.getInstance();

        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueRemoveSickJoints.getInstance());
    }
}
