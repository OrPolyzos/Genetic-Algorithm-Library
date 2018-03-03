package ga;

import convex_hull.domain.Point;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;

import java.util.List;
import java.util.Map;

public interface DNA {

    DNA mutate(MutationTechnique mutationTechnique);

    Map<Integer,List<Point>> getGene();

    double calculateFitness();

    FitnessTechnique getFitnessTechnique();

    int getIntersections();
}
