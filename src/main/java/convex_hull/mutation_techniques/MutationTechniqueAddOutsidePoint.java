package convex_hull.mutation_techniques;

import convex_hull.CHDNA;
import domain.Point;
import ga.DNA;
import ga.MutationTechnique;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class MutationTechniqueAddOutsidePoint implements MutationTechnique {

    private static MutationTechniqueAddOutsidePoint mutationTechniqueAddOutsidePoint;

    private MutationTechniqueAddOutsidePoint() {
    }

    public static synchronized MutationTechniqueAddOutsidePoint getInstance() {
        if (mutationTechniqueAddOutsidePoint == null) {
            mutationTechniqueAddOutsidePoint = new MutationTechniqueAddOutsidePoint();
        }
        return mutationTechniqueAddOutsidePoint;
    }

    @Override
    public DNA execute(DNA dnaToMutate) {
        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        List<Point> outsidePoints = dnaToMutate.getOutsidePoints();
        if (!outsidePoints.isEmpty()) {
            int chosenOne = new Random().nextInt(outsidePoints.size());
            int randomIndex = new Random().nextInt(mutatedHull.size());
            mutatedHull.add(randomIndex, new Point(outsidePoints.get(chosenOne)));
        }
        return new CHDNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getMutationTechniqueMap(), dnaToMutate.getFitnessTechnique());
//        return mutatedHull;
    }
}
