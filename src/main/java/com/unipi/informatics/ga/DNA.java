package com.unipi.informatics.ga;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.List;
import java.util.Map;

public interface DNA {

    DNA mutate(MutationTechnique mutationTechnique);

    double calculateFitness(FitnessTechnique fitnessTechnique);

    Map<Integer,List<Point>> getGene();

    int getIntersections();
}
