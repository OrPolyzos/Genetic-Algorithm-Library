package com.unipi.informatics.convex_hull.ch_ga.techniques.mutation;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Dna;
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
    public Dna<CH_Gene> execute(Dna<CH_Gene> dnaToMutate) {
        CH_Gene geneMap = dnaToMutate.getGene();
        List<Point> points = geneMap.getPoints();
        List<Point> mutatedHull = new ArrayList<>(geneMap.getConvexHull());
        List<Point> sickJoints = geneMap.getSickJoints();

        if (mutatedHull.size() > 3 && !sickJoints.isEmpty()) {
            mutatedHull.removeAll(sickJoints);
            CH_Gene newGeneMap = new CH_Gene(points, mutatedHull);
            return new CH_Dna(newGeneMap);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveSickJoints";
    }
}
