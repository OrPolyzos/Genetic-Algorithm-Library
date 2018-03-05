package com.unipi.informatics.convex_hull.ch_ga;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.ConvexHullUtilities;
import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CH_DNA extends DNA<Map<Integer,List<Point>>> {

    private Map<Integer, List<Point>> geneMap = new LinkedHashMap<>();
    private int intersections;

    public CH_DNA(List<Point> points, List<Point> convexHull) {

        List<Point> outsidePoints = ConvexHullUtilities.calculateOutsidePoints(convexHull, points);
        List<Point> sickJoints = ConvexHullUtilities.calculateSickJoints(convexHull);

        geneMap.put(0, points);
        geneMap.put(1, convexHull);
        geneMap.put(2, outsidePoints);
        geneMap.put(3, sickJoints);

        intersections = ConvexHullUtilities.calculateIntersections(convexHull);

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
    public DNA<Map<Integer,List<Point>>> mutate(MutationTechnique<Map<Integer,List<Point>>> mutationTechnique) {
        return mutationTechnique.execute(this);
    }

}
