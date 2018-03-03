package convex_hull.ch_ga;

import convex_hull.domain.Point;
import convex_hull.utilities.ConvexHullUtilities;
import ga.DNA;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CH_DNA implements DNA {

    private Map<Integer, List<Point>> geneMap = new LinkedHashMap<>();
    private int intersections;
    private FitnessTechnique fitnessTechnique;

    public CH_DNA(List<Point> points, List<Point> convexHull, FitnessTechnique fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;

        List<Point> outsidePoints = ConvexHullUtilities.calculateOutsidePoints(convexHull, points);
        List<Point> sickJoints = ConvexHullUtilities.calculateSickJoints(convexHull);

        geneMap.put(0,points);
        geneMap.put(1, convexHull);
        geneMap.put(2, outsidePoints);
        geneMap.put(3, sickJoints);

        intersections = ConvexHullUtilities.calculateIntersections(convexHull);

    }

    @Override
    public FitnessTechnique getFitnessTechnique() {
        return fitnessTechnique;
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
    public double calculateFitness() {
        return fitnessTechnique.calculateFitness(this);
    }

    @Override
    public DNA mutate(MutationTechnique mutationTechnique) {
        DNA mutatedDNA = mutationTechnique.execute(this);
        return mutatedDNA;
    }

}
