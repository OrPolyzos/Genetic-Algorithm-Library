package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationTechniqueAddOutsidePoint implements MutationTechnique<CH_Gene> {

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
    public void mutate(Dna<CH_Gene> dnaToMutate) {
        CH_Gene ch_gene = dnaToMutate.getGene();
        List<Point> outsidePoints = ch_gene.getOutsidePoints();
        if (!outsidePoints.isEmpty()) {
            List<Point> mutatedHull = ch_gene.getConvexHull();
            int chosenOutsidePoint = new Random().nextInt(outsidePoints.size());
            int randomIndex = new Random().nextInt(mutatedHull.size());
            mutatedHull.add(randomIndex, outsidePoints.get(chosenOutsidePoint));
            ch_gene.setConvexHull(mutatedHull);
        }
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueAddOutsidePoint";
    }
}
