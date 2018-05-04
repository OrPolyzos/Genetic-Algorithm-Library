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
        if (dna.getGene().getConvexHull().size() < 3) {
            return 0;
        }

        CH_Gene geneMap = dna.getGene();
        int fitOutsidePoints = geneMap.getOutsidePoints().size() + 1;
        int fitIntersections = geneMap.getIntersectionPoints().size() + 1;

        return 1 / (double) (fitOutsidePoints * fitIntersections);
    }

    @Override
    public String toString() {
        return "Using: FitnessTechniqueWithoutSickJoints";
    }
}
