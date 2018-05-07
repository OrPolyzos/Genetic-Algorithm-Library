package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.ArrayList;
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
    public Dna<CH_Gene> mutate(Dna<CH_Gene> dnaToMutate) {
        CH_Gene ch_gene = dnaToMutate.getGene();
        List<Point> points = ch_gene.getPoints();
        List<Point> mutatedHull = new ArrayList<>(ch_gene.getConvexHull());
        List<Point> sickJoints = ch_gene.getSickJoints();
        if (mutatedHull.size() > 3 && !sickJoints.isEmpty()) {
            mutatedHull.removeAll(sickJoints);
            CH_Gene mutated_ch_gene = new CH_Gene(points, mutatedHull);
            return new Dna<>(mutated_ch_gene);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveSickJoints";
    }
}
