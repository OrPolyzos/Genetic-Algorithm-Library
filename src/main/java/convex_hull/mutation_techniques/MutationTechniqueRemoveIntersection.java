package convex_hull.mutation_techniques;

import convex_hull.CHDNA;
import domain.Point;
import ga.DNA;
import ga.MutationTechnique;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class MutationTechniqueRemoveIntersection implements MutationTechnique {

    private static MutationTechniqueRemoveIntersection mutationTechniqueRemoveIntersection;

    private MutationTechniqueRemoveIntersection() {
    }

    public static synchronized MutationTechniqueRemoveIntersection getInstance() {
        if (mutationTechniqueRemoveIntersection == null) {
            mutationTechniqueRemoveIntersection = new MutationTechniqueRemoveIntersection();
        }
        return mutationTechniqueRemoveIntersection;
    }

    @Override
    public DNA execute(DNA dnaToMutate) {
        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        int chosen = new Random().nextInt(mutatedHull.size() - 1);
        mutatedHull.remove(chosen);
        mutatedHull.remove(chosen++);
        return new CHDNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getMutationTechniqueMap(), dnaToMutate.getFitnessTechnique());

//        return mutatedHull;
    }
}
