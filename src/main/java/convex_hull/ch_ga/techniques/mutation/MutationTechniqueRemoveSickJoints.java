package convex_hull.ch_ga.techniques.mutation;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import ga.DNA;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Map<Integer,List<Point>> geneMap = dnaToMutate.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));
        List<Point> sickJoints = geneMap.get(3);

        if (mutatedHull.size() > 3 && !sickJoints.isEmpty()) {

            FitnessTechnique fitnessTechnique = dnaToMutate.getFitnessTechnique();

            mutatedHull.removeAll(sickJoints);

            return new CH_DNA(points, mutatedHull, fitnessTechnique);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveSickJoints";
    }
}
