package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.CH_DNA;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MutationTechniqueRemoveSickJoints implements MutationTechnique<Map<Integer, List<Point>>> {

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
    public DNA<Map<Integer, List<Point>>> execute(DNA<Map<Integer, List<Point>>> dnaToMutate) {
        Map<Integer, List<Point>> geneMap = dnaToMutate.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));
        List<Point> sickJoints = geneMap.get(3);

        if (mutatedHull.size() > 3 && !sickJoints.isEmpty()) {

            mutatedHull.removeAll(sickJoints);

            return new CH_DNA(points, mutatedHull);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveSickJoints";
    }
}
