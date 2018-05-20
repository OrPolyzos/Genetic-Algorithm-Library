package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationTechniqueRemoveRandomPoint implements MutationTechnique<CH_Gene> {

    private static MutationTechniqueRemoveRandomPoint mutationTechniqueRemoveRandomPoint;

    private MutationTechniqueRemoveRandomPoint() {
    }

    public static synchronized MutationTechniqueRemoveRandomPoint getInstance() {
        if (mutationTechniqueRemoveRandomPoint == null) {
            mutationTechniqueRemoveRandomPoint = new MutationTechniqueRemoveRandomPoint();
        }
        return mutationTechniqueRemoveRandomPoint;
    }

    @Override
    public void mutate(Dna<CH_Gene> dnaToMutate) {
        CH_Gene ch_gene = dnaToMutate.getGene();
        List<Point> mutatedHull = ch_gene.getConvexHull();
        if (mutatedHull.size() > 3) {
            mutatedHull.remove(mutatedHull.get(new Random().nextInt(mutatedHull.size())));
            ch_gene.setConvexHull(mutatedHull);
        }
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveSickJoints";
    }
}
