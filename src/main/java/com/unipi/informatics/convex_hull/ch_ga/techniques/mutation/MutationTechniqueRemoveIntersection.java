package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.ArrayList;
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
            Point intersectionPointA = randomIntersectionsList.get(1);
            Point intersectionPointB = randomIntersectionsList.get(2);
            for (int i = 0; i < mutatedHull.size(); i++) {
                if (mutatedHull.get(i).equals(intersectionPointA)) {
                    mutatedHull.set(i, intersectionPointB);
                } else if (mutatedHull.get(i).equals(intersectionPointB)) {
                    mutatedHull.set(i, intersectionPointA);
                }
            }
            ch_gene.setConvexHull(mutatedHull);
        }
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveIntersection";
    }
}
