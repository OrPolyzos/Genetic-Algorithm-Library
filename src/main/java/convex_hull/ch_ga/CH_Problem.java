package convex_hull.ch_ga;

import convex_hull.domain.Point;
import convex_hull.utilities.ConvexHullUtilities;
import ga.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CH_Problem implements Problem {

    private List<Point> convexHull;
    private List<Point> points;
    private List<Point> outsidePoints;

    public CH_Problem(List<Point> convexHull, List<Point> points) {
        this.convexHull = convexHull;
        this.points = points;
    }

    @Override
    public Problem getCopy() {
        return new CH_Problem(new ArrayList<>(convexHull), new ArrayList<>(points));
    }

    @Override
    public double calculateFitness() {
        int fitOutsidePoints = outsidePoints.size() + 1;
        int fitIntersections = ConvexHullUtilities.calculateIntersections(convexHull) + 1;
        int fitSickJoints = ConvexHullUtilities.calculateSickJoints(convexHull).size() + 1;
        if (convexHull.size() < 3) {
            return 0;
        }
        double fitness = (1 / (double) (fitOutsidePoints * fitIntersections * fitSickJoints));
        return fitness;
    }

    @Override
    public Problem mutate() {
        double mutationChance = new Random().nextDouble();
        List<Point> mutatedHull = new ArrayList<Point>(convexHull);
        if (mutationChance < ((double) 1 / 3)) {
            int chosenPosition = new Random().nextInt(mutatedHull.size());
            mutatedHull.remove(chosenPosition);
        } else if (mutationChance < (double) 2 / 3) {
            int randomElement = new Random().nextInt(outsidePoints.size());
            int chosenPos = new Random().nextInt(mutatedHull.size());
            mutatedHull.add(chosenPos, new Point(outsidePoints.get(randomElement)));
        } else {
            int chosenOne = new Random().nextInt(outsidePoints.size());
            int toBeRemovedIndex = new Random().nextInt(convexHull.size());
            mutatedHull.set(toBeRemovedIndex, new Point(outsidePoints.get(chosenOne)));
        }
        return new CH_Problem(new ArrayList<>(mutatedHull), points);
    }

    @Override
    public List<Point> getGene() {
        return new ArrayList<>(convexHull);
    }
}
