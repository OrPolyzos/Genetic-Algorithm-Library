package convex_hull.ch_ga;

import convex_hull.domain.Point;
import convex_hull.utilities.ConvexHullUtilities;
import ga.domain.DNA;
import ga.domain.GeneticAlgorithm;

import java.util.*;

public class CH_DNA implements DNA {

    private List<Point> convexHull;
    private List<Point> points;
    private List<Point> outsidePoints;
    private List<Point> sickJoints = new ArrayList<>();
    private int intersections;

    private GeneticAlgorithm geneticAlgorithm;

    public CH_DNA(Set<Point> convexHull, List<Point> points, GeneticAlgorithm geneticAlgorithm) {
        this.convexHull = new ArrayList<>(convexHull);
        this.points = new ArrayList<>(points);
        this.geneticAlgorithm = geneticAlgorithm;

        outsidePoints = ConvexHullUtilities.calculateOutsidePoints(this.convexHull, points);
        intersections = ConvexHullUtilities.calculateIntersections(this.convexHull);
        if (this.convexHull.size() >= 3) {
            sickJoints = ConvexHullUtilities.calculateSickJoints(this.convexHull);
        }

    }

    public GeneticAlgorithm getGeneticAlgorithm() {
        return geneticAlgorithm;
    }

    public void setGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm) {
        this.geneticAlgorithm = geneticAlgorithm;
    }

    @Override
    public DNA getCopy() {
        return new CH_DNA(new LinkedHashSet<>(convexHull), new ArrayList<>(points), geneticAlgorithm);
    }

    @Override
    public List<Point> getGene() {
        return convexHull;
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public List<Point> getSickJoints() {
        return sickJoints;
    }

    @Override
    public List<Point> getOutsidePoints() {
        return outsidePoints;
    }

    @Override
    public int getIntersections() {
        return intersections;
    }

    @Override
    public double calculateFitness() {
        return geneticAlgorithm.getFitnessTechnique().calculateFitness(this);
    }

    @Override
    public DNA mutate() {
        int mutationChance = new Random().nextInt(geneticAlgorithm.getMutationTechniqueMap().size());
        DNA mutatedDNA = geneticAlgorithm.getMutationTechniqueMap().get(mutationChance).execute(this);
        if (mutatedDNA.getIntersections() == 0) {
            return mutatedDNA;
        } else {
            return this;
        }
    }

}
