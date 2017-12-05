package convex_hull.mutation_techniques;

import convex_hull.CHDNA;
import domain.Point;
import ga.DNA;
import ga.MutationTechnique;
import utilities.ConvexHullUtilities;

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
        return new CHDNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getMutationTechniqueMap(), dnaToMutate.getFitnessTechnique());

//        return mutatedHull;
    }
}
