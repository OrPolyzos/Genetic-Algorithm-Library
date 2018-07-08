package com.algorithm.genetic.convex_hull.ch_ga.techniques.fitness;

import com.algorithm.genetic.convex_hull.ch_ga.domain.CH_Gene;
import com.algorithm.genetic.library.ga.domain.Dna;
import com.algorithm.genetic.library.ga.techniques.FitnessTechnique;

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
        CH_Gene ch_gene = dna.getGene();
        if (ch_gene.getConvexHull().size() < 3) {
            return 0;
        }

        double fitOutsidePoints = Math.pow(ch_gene.getOutsidePoints().size() + 1, 1);
        double fitIntersections = Math.pow(ch_gene.getIntersectionPoints().size() + 1, 1);
        double fitSickJoints = Math.pow(ch_gene.getSickJoints().size() + 1, 1);
        return 1 / (fitIntersections * fitOutsidePoints * fitSickJoints);
    }

    @Override
    public String toString() {
        return "Using: FitnessTechniqueForEachLetter";
    }
}
