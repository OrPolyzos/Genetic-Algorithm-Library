package convex_hull.mutation_techniques;

import convex_hull.CHDNA;
import domain.Point;
import ga.DNA;
import ga.MutationTechnique;

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
        return new CHDNA(new LinkedHashSet<>(mutatedHull), dnaToMutate.getPoints(), dnaToMutate.getMutationTechniqueMap(), dnaToMutate.getFitnessTechnique());

//        return mutatedHull;
    }
}
