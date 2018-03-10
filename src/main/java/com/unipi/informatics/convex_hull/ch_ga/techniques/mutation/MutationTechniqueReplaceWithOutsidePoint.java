package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Dna;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MutationTechniqueReplaceWithOutsidePoint implements MutationTechnique<Map<Integer, List<Point>>> {

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
    public Dna<Map<Integer, List<Point>>> execute(Dna<Map<Integer, List<Point>>> dnaToMutate) {

        Map<Integer, List<Point>> geneMap = dnaToMutate.getGene();
        List<Point> outsidePoints = geneMap.get(2);

        if (!outsidePoints.isEmpty()) {
            List<Point> points = geneMap.get(0);
            List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));

            Point chosenOutsidePoint = outsidePoints.get(new Random().nextInt(outsidePoints.size()));
            int closestCurrentPoint = CH_Utilities.findClosest(chosenOutsidePoint, mutatedHull);
            mutatedHull.set(closestCurrentPoint, chosenOutsidePoint);

            return new CH_Dna(points, mutatedHull);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueReplaceWithOutsidePoint";
    }
}
