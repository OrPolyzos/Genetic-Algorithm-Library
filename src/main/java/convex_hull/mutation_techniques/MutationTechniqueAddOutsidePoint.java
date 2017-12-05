package convex_hull.mutation_techniques;

import domain.Point;
import ga.DNA;
import ga.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationTechniqueAddOutsidePoint implements MutationTechnique {

    @Override
    public List<Point> execute(DNA dnaToMutate) {

        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        List<Point> outsidePoints = dnaToMutate.getOutsidePoints();
        if (!outsidePoints.isEmpty()) {
            int chosenOne = new Random().nextInt(outsidePoints.size());
            int randomIndex = new Random().nextInt(mutatedHull.size());
            mutatedHull.add(randomIndex, new Point(outsidePoints.get(chosenOne)));
        }
        return mutatedHull;
    }
}
