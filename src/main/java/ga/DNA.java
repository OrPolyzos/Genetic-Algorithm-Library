package ga;


import convex_hull.domain.Point;
import ga.domain.GeneticAlgorithm;

import java.util.List;

public interface DNA {

    DNA getCopy();

    DNA mutate();

    List<Point> getPoints();

    List<Point> getSickJoints();

    int getIntersections();


    List<Point> getGene();

    double calculateFitness();

    List<Point> getOutsidePoints();

    GeneticAlgorithm getGeneticAlgorithm();

    void setGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm);


}
