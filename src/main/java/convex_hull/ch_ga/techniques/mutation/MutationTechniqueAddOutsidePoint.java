package convex_hull.ch_ga.techniques.mutation;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import ga.domain.DNA;
import ga.techniques.MutationTechnique;

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
        return new CH_DNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getGeneticAlgorithm());
//        return mutatedHull;
    }
}
