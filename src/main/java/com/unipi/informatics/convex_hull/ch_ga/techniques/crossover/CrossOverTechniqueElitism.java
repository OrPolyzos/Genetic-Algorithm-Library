package com.unipi.informatics.convex_hull.ch_ga.techniques.crossover;

import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.techniques.CrossOverTechnique;

public class CrossOverTechniqueElitism implements CrossOverTechnique {

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
    public Chromosome crossOver(Chromosome parentA, Chromosome parentB) {
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
