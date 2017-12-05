package convex_hull.mutation_techniques;

import domain.Point;
import ga.DNA;
import ga.MutationTechnique;
import utilities.ConvexHullUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationTechniqueReplaceWithOutsidePoint implements MutationTechnique {
    @Override
    public List<Point> execute(DNA dnaToMutate) {
        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        List<Point> outsidePoints = dnaToMutate.getOutsidePoints();
        if (!outsidePoints.isEmpty()) {
            Point chosenPoint = outsidePoints.get(new Random().nextInt(outsidePoints.size()));
            int toBeReplacedIndex = ConvexHullUtilities.findClosest(chosenPoint, mutatedHull);
            mutatedHull.set(toBeReplacedIndex, new Point(chosenPoint));
        }

        return mutatedHull;
    }
}
