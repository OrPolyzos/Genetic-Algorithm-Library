package com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.crossover;

import com.algorithm.genetic.library.ga.domain.Chromosome;
import com.algorithm.genetic.library.ga.domain.Dna;
import com.algorithm.genetic.library.ga.techniques.CrossOverTechnique;
import com.algorithm.genetic.library.ga.techniques.FitnessTechnique;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_Gene;

public class CrossOverTechniqueHalfWay implements CrossOverTechnique<IMT_Gene> {

    private static CrossOverTechniqueHalfWay crossOverTechniqueHalfWay;

    private CrossOverTechniqueHalfWay() {
    }

    public static synchronized CrossOverTechniqueHalfWay getInstance() {
        if (crossOverTechniqueHalfWay == null) {
            crossOverTechniqueHalfWay = new CrossOverTechniqueHalfWay();
        }
        return crossOverTechniqueHalfWay;
    }

    @Override
    public Chromosome<IMT_Gene> crossOver(Chromosome<IMT_Gene> parentA, Chromosome<IMT_Gene> parentB) {

        StringBuilder newPhrase = new StringBuilder();
        for (int i = 0; i < parentA.getDna().getGene().getTargetPhrase().length(); i++) {
            if (i % 2 == 0) {
                newPhrase.append(parentA.getDna().getGene().getOwnPhrase().charAt(i));
            } else {
                newPhrase.append(parentB.getDna().getGene().getOwnPhrase().charAt(i));
            }
        }
        FitnessTechnique<IMT_Gene> fitnessTechnique = parentA.getFitnessTechnique();
        IMT_Gene childGene = new IMT_Gene(parentA.getDna().getGene().getTargetPhrase(), newPhrase.toString());
        Dna<IMT_Gene> childDna = new Dna<>(childGene);
        return new Chromosome<>(childDna, fitnessTechnique);
    }

    @Override
    public String toString() {
        return "Using: CrossOverTechniqueHalfWay";
    }
}
