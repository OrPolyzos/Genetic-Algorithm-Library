package convex_hull.ch_ga.techniques.crossover;

import ga.domain.Chromosome;
import ga.techniques.CrossOverTechnique;

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
