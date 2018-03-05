package com.unipi.informatics.convex_hull.ch_ga.techniques.crossover;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.techniques.CrossOverTechnique;

import java.util.List;
import java.util.Map;

public class CrossOverTechniqueElitism implements CrossOverTechnique<Map<Integer, List<Point>>> {

    private static CrossOverTechniqueElitism crossOverTechniqueElitism;

    private CrossOverTechniqueElitism() {
    }

    public static synchronized CrossOverTechniqueElitism getInstance() {
        if (crossOverTechniqueElitism == null) {
            crossOverTechniqueElitism = new CrossOverTechniqueElitism();
        }
        return crossOverTechniqueElitism;
    }

    @Override
    public Chromosome<Map<Integer, List<Point>>> crossOver(Chromosome<Map<Integer, List<Point>>> parentA, Chromosome<Map<Integer, List<Point>>> parentB) {
        if (parentA.getFitness() > parentB.getFitness()) {
            return parentA;
        } else {
            return parentB;
        }
    }

    @Override
    public String toString() {
        return "Using: CrossOverTechniqueElitism";
    }
}
