package com.unipi.informatics.convex_hull.ch_ga.techniques.fitness;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.techniques.FitnessTechnique;

import java.util.List;
import java.util.Map;

public class FitnessTechniqueWithSickJoints implements FitnessTechnique {

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
    public double calculateFitness(DNA dna) {
        if (dna.getGene().size() < 3) {
            return 0;
        }

        Map<Integer,List<Point>> geneMap = dna.getGene();
        List<Point> outsidePoints = geneMap.get(2);
        List<Point> sickJoints = geneMap.get(3);

        int fitOutsidePoints = outsidePoints.size() + 1;
        int fitSickJoints = sickJoints.size() + 1;
        int fitIntersections = dna.getIntersections() + 1;

        return 1 / (double) (fitIntersections * fitOutsidePoints * fitSickJoints);
    }

    @Override
    public String toString() {
        return "Using: FitnessTechniqueWithSickJoints";
    }
}
