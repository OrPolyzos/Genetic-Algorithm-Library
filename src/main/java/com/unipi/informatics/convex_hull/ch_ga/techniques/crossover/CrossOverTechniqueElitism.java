package com.unipi.informatics.convex_hull.ch_ga.techniques.crossover;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.techniques.CrossOverTechnique;

public class CrossOverTechniqueElitism implements CrossOverTechnique<CH_Gene> {

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
    public Chromosome<CH_Gene> crossOver(Chromosome<CH_Gene> parentA, Chromosome<CH_Gene> parentB) {
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
