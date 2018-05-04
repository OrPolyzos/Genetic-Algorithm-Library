package com.unipi.informatics.convex_hull.ch_ga.techniques.fitness;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.FitnessTechnique;

public class FitnessTechniqueWithSickJoints implements FitnessTechnique<CH_Gene> {

    private static FitnessTechniqueWithSickJoints fitnessTechniqueWithSickJoints;

    private FitnessTechniqueWithSickJoints() {
    }

    public static synchronized FitnessTechniqueWithSickJoints getInstance() {
        if (fitnessTechniqueWithSickJoints == null) {
            fitnessTechniqueWithSickJoints = new FitnessTechniqueWithSickJoints();
        }
        return fitnessTechniqueWithSickJoints;
    }

    @Override
    public double calculateFitness(Dna<CH_Gene> dna) {
        if (dna.getGene().getConvexHull().size() < 3) {
            return 0;
        }

        CH_Gene geneMap = dna.getGene();

        int fitOutsidePoints = geneMap.getOutsidePoints().size() + 1;
        int fitSickJoints = geneMap.getSickJoints().size() + 1;
        int fitIntersections = geneMap.getIntersectionPoints().size() + 1;

        return 1 / (double) (fitIntersections * fitOutsidePoints * fitSickJoints);
    }

    @Override
    public String toString() {
        return "Using: FitnessTechniqueWithSickJoints";
    }
}
