package com.unipi.informatics.convex_hull.ch_ga.techniques.fitness;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.FitnessTechnique;

import java.util.List;
import java.util.Map;

public class FitnessTechniqueWithoutSickJoints implements FitnessTechnique<Map<Integer, List<Point>>> {

    private static FitnessTechnique fitnessTechniqueWithoutSickJoints;

    private FitnessTechniqueWithoutSickJoints() {
    }

    public static synchronized FitnessTechnique getInstance() {
        if (fitnessTechniqueWithoutSickJoints == null) {
            fitnessTechniqueWithoutSickJoints = new FitnessTechniqueWithoutSickJoints();
        }
        return fitnessTechniqueWithoutSickJoints;
    }

    @Override
    public double calculateFitness(Dna<Map<Integer, List<Point>>> dna) {
        if (dna.getGene().size() < 3) {
            return 0;
        }

        Map<Integer, List<Point>> geneMap = dna.getGene();
        List<Point> outsidePoints = geneMap.get(2);
        List<Point> intersectionPoints = geneMap.get(4);
        int fitOutsidePoints = outsidePoints.size() + 1;
        int fitIntersections = intersectionPoints.size() + 1;

        return 1 / (double) (fitOutsidePoints * fitIntersections);
    }

    @Override
    public String toString() {
        return "Using: FitnessTechniqueWithoutSickJoints";
    }
}
