package com.unipi.informatics.convex_hull.ch_ga.techniques.fitness;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.FitnessTechnique;

public class FitnessTechniqueWithoutSickJoints implements FitnessTechnique<CH_Gene> {

    private static FitnessTechniqueWithoutSickJoints fitnessTechniqueWithoutSickJoints;

    private FitnessTechniqueWithoutSickJoints() {
    }

    public static synchronized FitnessTechniqueWithoutSickJoints getInstance() {
        if (fitnessTechniqueWithoutSickJoints == null) {
            fitnessTechniqueWithoutSickJoints = new FitnessTechniqueWithoutSickJoints();
        }
        return fitnessTechniqueWithoutSickJoints;
    }

    @Override
    public double calculateFitness(Dna<CH_Gene> dna) {
        CH_Gene ch_gene = dna.getGene();
        if (ch_gene.getConvexHull().size() < 3) {
            return 0;
        }

        double fitOutsidePoints = Math.pow(ch_gene.getOutsidePoints().size() + 1, 3);
        double fitIntersections = Math.pow(ch_gene.getIntersectionPoints().size() + 1, 2);
        return 1 / (fitOutsidePoints * fitIntersections);
    }

    @Override
    public String toString() {
        return "Using: FitnessTechniqueWithoutSickJoints";
    }
}
