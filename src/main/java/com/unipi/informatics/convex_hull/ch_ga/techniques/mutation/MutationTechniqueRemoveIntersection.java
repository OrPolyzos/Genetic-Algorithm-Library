package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MutationTechniqueRemoveIntersection implements MutationTechnique<CH_Gene> {

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
    public void mutate(Dna<CH_Gene> dnaToMutate) {
        CH_Gene ch_gene = dnaToMutate.getGene();
        List<Point> mutatedHull = ch_gene.getConvexHull();
        List<List<Point>> intersectionsLists = ch_gene.getIntersectionPoints();
        if (mutatedHull.size() > 3 && !intersectionsLists.isEmpty()) {
            List<Point> randomIntersectionsList = intersectionsLists.get(new Random().nextInt(intersectionsLists.size()));
            double whatToSwap = new Random().nextDouble();
            Point intersectionPointA;
            Point intersectionPointB;
            if (whatToSwap < 0.5) {
                intersectionPointA = randomIntersectionsList.get(0);
                intersectionPointB = randomIntersectionsList.get(3);
            } else {
                intersectionPointA = randomIntersectionsList.get(1);
                intersectionPointB = randomIntersectionsList.get(2);
            }
            int indexA = 0;
            int indexB = 0;
            for (int i = 0; i < mutatedHull.size(); i++) {
                if (mutatedHull.get(i).equals(intersectionPointA)) {
                    indexA = i;
                } else if (mutatedHull.get(i).equals(intersectionPointB)) {
                    indexB = i;
                }
            }
            Collections.swap(mutatedHull, indexA, indexB);
            ch_gene.setConvexHull(mutatedHull);
        }
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveIntersection";
    }
}
