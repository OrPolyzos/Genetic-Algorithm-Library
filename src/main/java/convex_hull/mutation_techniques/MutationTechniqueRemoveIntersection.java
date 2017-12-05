package convex_hull.mutation_techniques;

import domain.Point;
import ga.DNA;
import ga.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationTechniqueRemoveIntersection implements MutationTechnique {

    @Override
    public List<Point> execute(DNA dnaToMutate) {
        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        int chosen = new Random().nextInt(mutatedHull.size() - 1);
        mutatedHull.remove(chosen);
        mutatedHull.remove(chosen++);
        return mutatedHull;
    }
}
