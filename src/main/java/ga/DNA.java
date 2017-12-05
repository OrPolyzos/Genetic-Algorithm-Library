package ga;


import domain.Point;

import java.util.List;
import java.util.Map;

public interface DNA {

    DNA getCopy();

    DNA mutate();

    List<Point> getPoints();

    Map<Integer, MutationTechnique> getMutationTechniqueMap();

    List<Point> getSickJoints();

    int getIntersections();

    List<Point> getGene();

    double calculateFitness();

    List<Point> getOutsidePoints();


}
