package com.algorithm.genetic.convex_hull.ch_ga.techniques.mutation;

import com.algorithm.genetic.convex_hull.ch_ga.domain.CH_Gene;
import com.algorithm.genetic.convex_hull.domain.Point;
import com.algorithm.genetic.library.ga.domain.Dna;
import com.algorithm.genetic.library.ga.techniques.MutationTechnique;

import java.util.List;

public class MutationTechniqueRemoveSickJoints implements MutationTechnique<CH_Gene> {

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
    public void mutate(Dna<CH_Gene> dnaToMutate) {
        CH_Gene ch_gene = dnaToMutate.getGene();
        List<Point> mutatedHull = ch_gene.getConvexHull();
        List<Point> sickJoints = ch_gene.getSickJoints();
        if (mutatedHull.size() > 3 && !sickJoints.isEmpty()) {
            mutatedHull.removeAll(sickJoints);
            ch_gene.setConvexHull(mutatedHull);
        }
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveSickJoints";
    }
}
