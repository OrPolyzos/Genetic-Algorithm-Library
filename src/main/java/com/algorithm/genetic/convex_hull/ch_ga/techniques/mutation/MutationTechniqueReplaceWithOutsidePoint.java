package com.algorithm.genetic.convex_hull.ch_ga.techniques.mutation;

import com.algorithm.genetic.convex_hull.ch_ga.domain.CH_Gene;
import com.algorithm.genetic.convex_hull.domain.Point;
import com.algorithm.genetic.convex_hull.utilities.CH_Utilities;
import com.algorithm.genetic.ga.domain.Dna;
import com.algorithm.genetic.ga.techniques.MutationTechnique;

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
    public void mutate(Dna<CH_Gene> dnaToMutate) {
        CH_Gene ch_gene = dnaToMutate.getGene();
        List<Point> outsidePoints = ch_gene.getOutsidePoints();
        if (!outsidePoints.isEmpty()) {
            List<Point> mutatedHull = ch_gene.getConvexHull();

            Point chosenOutsidePoint = outsidePoints.get(new Random().nextInt(outsidePoints.size()));
            int closestCurrentPoint = CH_Utilities.findClosest(chosenOutsidePoint, mutatedHull);
            mutatedHull.set(closestCurrentPoint, chosenOutsidePoint);
            ch_gene.setConvexHull(mutatedHull);
        }
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueReplaceWithRandomLetterFromKeyboard";
    }
}
