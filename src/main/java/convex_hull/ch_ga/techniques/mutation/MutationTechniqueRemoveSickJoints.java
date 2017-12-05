package convex_hull.ch_ga.techniques.mutation;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import ga.domain.DNA;
import ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class MutationTechniqueRemoveSickJoints implements MutationTechnique {

    private static MutationTechniqueRemoveSickJoints mutationTechniqueRemoveSickJoints;

    private MutationTechniqueRemoveSickJoints() {
    }

    public static synchronized MutationTechniqueRemoveSickJoints getInstance() {
        if (mutationTechniqueRemoveSickJoints == null) {
            mutationTechniqueRemoveSickJoints = new MutationTechniqueRemoveSickJoints();
        }
        return mutationTechniqueRemoveSickJoints;
    }

    @Override
    public DNA execute(DNA dnaToMutate) {
        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        List<Point> sickJoints = dnaToMutate.getSickJoints();
        mutatedHull.removeAll(sickJoints);
        return new CH_DNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getGeneticAlgorithm());

//        return mutatedHull;
    }
}
