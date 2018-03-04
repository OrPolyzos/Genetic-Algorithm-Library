package com.unipi.informatics.ga;

import com.unipi.informatics.convex_hull.domain.Point;

import java.util.List;

public interface Problem {

    Problem getCopy();

    double calculateFitness();

    Problem mutate();

    List<Point> getGene();

}
