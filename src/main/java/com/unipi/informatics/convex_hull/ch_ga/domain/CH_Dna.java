package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CH_Dna extends Dna<Map<Integer,List<Point>>> {

    private Map<Integer, List<Point>> geneMap = new LinkedHashMap<>();
    private int intersections;

    public CH_Dna(List<Point> points, List<Point> convexHull) {

        List<Point> outsidePoints = CH_Utilities.calculateOutsidePoints(convexHull, points);
        List<Point> sickJoints = CH_Utilities.calculateSickJoints(convexHull);

        geneMap.put(0, points);
        geneMap.put(1, convexHull);
        geneMap.put(2, outsidePoints);
        geneMap.put(3, sickJoints);

        intersections = CH_Utilities.calculateIntersections(convexHull);

    }

    @Override
    public Map<Integer, List<Point>> getGene() {
        return geneMap;
    }

    @Override
    public int getIntersections() {
        return intersections;
    }

    @Override
    public double calculateFitness(FitnessTechnique<Map<Integer,List<Point>>> fitnessTechnique) {
        return fitnessTechnique.calculateFitness(this);
    }

    @Override
    public Dna<Map<Integer,List<Point>>> mutate(MutationTechnique<Map<Integer,List<Point>>> mutationTechnique) {
        return mutationTechnique.execute(this);
    }

}
