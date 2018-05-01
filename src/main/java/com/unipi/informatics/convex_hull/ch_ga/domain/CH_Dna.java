package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.List;
import java.util.Map;

public class CH_Dna extends Dna<Map<Integer, List<Point>>> {

    public CH_Dna(Map<Integer,List<Point>> geneMap) {
        super(geneMap);
        List<Point> points = geneMap.get(0);
        List<Point> convexHull = geneMap.get(1);
        List<Point> outsidePoints = CH_Utilities.calculateOutsidePoints(convexHull, points);
        List<Point> sickJoints = CH_Utilities.calculateSickJoints(convexHull);
        List<Point> intersectionPoints = CH_Utilities.calculateIntersections(convexHull);
        geneMap.put(2, outsidePoints);
        geneMap.put(3, sickJoints);
        geneMap.put(4, intersectionPoints);
    }

    @Override
    public double calculateFitness(FitnessTechnique<Map<Integer, List<Point>>> fitnessTechnique) {
        return fitnessTechnique.calculateFitness(this);
    }

    @Override
    public Dna<Map<Integer, List<Point>>> mutate(MutationTechnique<Map<Integer, List<Point>>> mutationTechnique) {
        return mutationTechnique.execute(this);
    }

}
