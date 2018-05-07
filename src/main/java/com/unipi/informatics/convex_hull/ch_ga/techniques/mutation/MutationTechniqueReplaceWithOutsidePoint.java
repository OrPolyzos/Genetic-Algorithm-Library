package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationTechniqueReplaceWithOutsidePoint implements MutationTechnique<CH_Gene> {

    private static MutationTechniqueReplaceWithOutsidePoint mutationTechniqueReplaceWithOutsidePoint;

    private MutationTechniqueReplaceWithOutsidePoint() {
    }

    public static synchronized MutationTechniqueReplaceWithOutsidePoint getInstance() {
        if (mutationTechniqueReplaceWithOutsidePoint == null) {
            mutationTechniqueReplaceWithOutsidePoint = new MutationTechniqueReplaceWithOutsidePoint();
        }
        return mutationTechniqueReplaceWithOutsidePoint;
    }

    @Override
    public Dna<CH_Gene> mutate(Dna<CH_Gene> dnaToMutate) {
        CH_Gene ch_gene = dnaToMutate.getGene();
        List<Point> outsidePoints = ch_gene.getOutsidePoints();

        if (!outsidePoints.isEmpty()) {
            List<Point> points = ch_gene.getPoints();
            List<Point> mutatedHull = new ArrayList<>(ch_gene.getConvexHull());

            Point chosenOutsidePoint = outsidePoints.get(new Random().nextInt(outsidePoints.size()));
            int closestCurrentPoint = CH_Utilities.findClosest(chosenOutsidePoint, mutatedHull);
            mutatedHull.set(closestCurrentPoint, chosenOutsidePoint);
            CH_Gene mutated_ch_gene = new CH_Gene(points, mutatedHull);
            return new Dna<>(mutated_ch_gene);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueReplaceWithOutsidePoint";
    }
}
