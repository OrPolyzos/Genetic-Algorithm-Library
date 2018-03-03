package convex_hull.ch_ga.techniques.mutation;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import convex_hull.utilities.ConvexHullUtilities;
import ga.DNA;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;

import java.util.*;

public class MutationTechniqueReplaceWithOutsidePoint implements MutationTechnique {

    private static MutationTechniqueReplaceWithOutsidePoint mutationTechniqueReplaceWithOutsidePoint;

    private MutationTechniqueReplaceWithOutsidePoint() {
    }

    public static synchronized MutationTechniqueReplaceWithOutsidePoint getInstance() {
        if (mutationTechniqueReplaceWithOutsidePoint == null) {
            mutationTechniqueReplaceWithOutsidePoint = new MutationTechniqueReplaceWithOutsidePoint();
        }
        return mutationTechniqueReplaceWithOutsidePoint;
    }

    @Override
    public DNA execute(DNA dnaToMutate) {
        Map<Integer,List<Point>> geneMap = dnaToMutate.getGene();
        List<Point> outsidePoints = geneMap.get(2);

        if (!outsidePoints.isEmpty()) {
            List<Point> points = geneMap.get(0);
            List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));

            FitnessTechnique fitnessTechnique = dnaToMutate.getFitnessTechnique();

            Point chosenOutsidePoint = outsidePoints.get(new Random().nextInt(outsidePoints.size()));
            int closestCurrentPoint = ConvexHullUtilities.findClosest(chosenOutsidePoint, mutatedHull);
            mutatedHull.set(closestCurrentPoint, chosenOutsidePoint);

            return new CH_DNA(points, mutatedHull, fitnessTechnique);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueReplaceWithOutsidePoint";
    }
}
