import convex_hull.CHGeneticAlgorithm;
import convex_hull.fitness_techniques.FitnessTechniqueWithSickJoints;
import convex_hull.fitness_techniques.FitnessTechniqueWithoutSickJoints;
import convex_hull.mutation_techniques.MutationTechniqueAddOutsidePoint;
import convex_hull.mutation_techniques.MutationTechniqueRemoveIntersection;
import convex_hull.mutation_techniques.MutationTechniqueRemoveSickJoints;
import convex_hull.mutation_techniques.MutationTechniqueReplaceWithOutsidePoint;
import domain.Point;
import ga.FitnessTechnique;
import ga.MutationTechnique;
import utilities.ConvexHullUtilities;
import view.Canvas;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainClass {

    private static double mutationRate = 1;
    private static int populationCount = 25;
    private static int pointsCount = 500;

    private static Map<Integer, MutationTechnique> mutationTechniqueMap = new LinkedHashMap<>();
    private static FitnessTechnique fitnessTechnique;

    public static void main(String[] args) {
        while (true) {
            Canvas canvas = new Canvas(800, 720);
            List<Point> points = ConvexHullUtilities.generatePoints(pointsCount, canvas);
            setupForPhase1();
            CHGeneticAlgorithm chGeneticAlgorithm = new CHGeneticAlgorithm(points, canvas, populationCount, fitnessTechnique, mutationRate, mutationTechniqueMap);
            chGeneticAlgorithm.initialGeneration();
            chGeneticAlgorithm.run();

            setupForPhase2();
            chGeneticAlgorithm.setFitnessTechnique(fitnessTechnique);
            chGeneticAlgorithm.eliteGeneration();
            chGeneticAlgorithm.run();
            System.out.println((chGeneticAlgorithm.getFittestChromosomeEver().getDNA().getIntersections()));
//            new Scanner(System.in).next();
        }
    }

    public static void setupForPhase1() {
        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueAddOutsidePoint.getInstance());
        mutationTechniqueMap.put(1, MutationTechniqueReplaceWithOutsidePoint.getInstance());
        mutationTechniqueMap.put(2, MutationTechniqueRemoveIntersection.getInstance());
        mutationTechniqueMap.put(3, MutationTechniqueRemoveSickJoints.getInstance());
        fitnessTechnique = FitnessTechniqueWithoutSickJoints.getInstance();
    }

    public static void setupForPhase2() {
        mutationTechniqueMap.clear();
        mutationTechniqueMap.put(0, MutationTechniqueRemoveSickJoints.getInstance());
        fitnessTechnique = FitnessTechniqueWithSickJoints.getInstance();
    }
}
