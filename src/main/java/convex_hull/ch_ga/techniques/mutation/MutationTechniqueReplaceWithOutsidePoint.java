package convex_hull.ch_ga.techniques.mutation;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import convex_hull.utilities.ConvexHullUtilities;
import ga.domain.DNA;
import ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

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
        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        List<Point> outsidePoints = dnaToMutate.getOutsidePoints();
        if (!outsidePoints.isEmpty()) {
            Point chosenPoint = outsidePoints.get(new Random().nextInt(outsidePoints.size()));
            int toBeReplacedIndex = ConvexHullUtilities.findClosest(chosenPoint, mutatedHull);
            mutatedHull.set(toBeReplacedIndex, new Point(chosenPoint));
        }
        return new CH_DNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getGeneticAlgorithm());

//        return mutatedHull;
    }
}
