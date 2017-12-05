package convex_hull.ch_ga.techniques.mutation;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import ga.domain.DNA;
import ga.techniques.MutationTechnique;

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
        return new CH_DNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getGeneticAlgorithm());

//        return mutatedHull;
    }
}
