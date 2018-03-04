package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.CH_DNA;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.*;

public class MutationTechniqueRemoveIntersection implements MutationTechnique {

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
    public DNA execute(DNA dnaToMutate) {
        Map<Integer,List<Point>> geneMap = dnaToMutate.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));

        if (mutatedHull.size() > 3) {
            int possibleIntersectionPoint = new Random().nextInt(mutatedHull.size() - 1);
            mutatedHull.remove(possibleIntersectionPoint);
            return new CH_DNA(points, mutatedHull);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveIntersection";
    }
}
