package convex_hull;

import domain.Point;
import ga.DNA;
import ga.FitnessTechnique;
import ga.MutationTechnique;
import utilities.ConvexHullUtilities;

import java.util.*;

public class CHDNA implements DNA {

    private List<Point> convexHull;
    private List<Point> points;
    private Map<Integer, MutationTechnique> mutationTechniqueMap;
    private List<Point> outsidePoints;
    private List<Point> sickJoints = new ArrayList<>();
    private int intersections;

    private FitnessTechnique fitnessTechnique;


    public CHDNA(Set<Point> convexHull, List<Point> points, Map<Integer, MutationTechnique> mutationTechniqueMap, FitnessTechnique fitnessTechnique) {
        this.convexHull = new ArrayList<>(convexHull);
        this.points = new ArrayList<>(points);
        this.mutationTechniqueMap = mutationTechniqueMap;
        outsidePoints = ConvexHullUtilities.calculateOutsidePoints(this.convexHull, points);
        intersections = ConvexHullUtilities.calculateIntersections(this.convexHull);
        if (this.convexHull.size() >= 3) {
            sickJoints = ConvexHullUtilities.calculateSickJoints(this.convexHull);
        }
        this.fitnessTechnique = fitnessTechnique;

    }

    @Override
    public DNA getCopy() {
        return new CHDNA(new LinkedHashSet<>(convexHull), new ArrayList<>(points), mutationTechniqueMap, fitnessTechnique);
    }

    @Override
    public List<Point> getGene() {
        return convexHull;
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }

    public List<Point> getSickJoints() {
        return sickJoints;
    }

    public List<Point> getOutsidePoints() {
        return outsidePoints;
    }

    public int getIntersections() {
        return intersections;
    }

    @Override
    public double calculateFitness() {
        return fitnessTechnique.calculateFitness(this);
    }

    @Override
    public Map<Integer, MutationTechnique> getMutationTechniqueMap() {
        return mutationTechniqueMap;
    }

    @Override
    public DNA mutate() {
//        DNA mutatedDNA = this.getCopy();
//        List<Point> mutatedHull;
//        for (int i =0; i < mutationTechniqueMap.size(); i++){
//            int mutationChance = new Random().nextInt(mutationTechniqueMap.size());
//            mutatedHull = mutationTechniqueMap.get(mutationChance).execute(mutatedDNA);
//            mutatedDNA = new CHDNA(new LinkedHashSet<>(mutatedHull), points, mutationTechniqueMap);
//        }
        int mutationChance = new Random().nextInt(mutationTechniqueMap.size());
        List<Point> mutatedHull = mutationTechniqueMap.get(mutationChance).execute(this);
        DNA mutatedDNA = new CHDNA(new LinkedHashSet<>(mutatedHull), points, mutationTechniqueMap, fitnessTechnique);
        if (mutatedDNA.getIntersections() == 0){
            return mutatedDNA;
        }
        else return this;
//        return mutatedDNA;
    }

}
