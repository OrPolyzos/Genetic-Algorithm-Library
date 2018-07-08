package com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.fitness;

import com.algorithm.genetic.ga.domain.Dna;
import com.algorithm.genetic.ga.techniques.FitnessTechnique;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_Gene;

public class FitnessTechniqueForEachLetter implements FitnessTechnique<IMT_Gene> {

    private static FitnessTechniqueForEachLetter fitnessTechniqueWithSickJoints;

    private FitnessTechniqueForEachLetter() {
    }

    public static synchronized FitnessTechniqueForEachLetter getInstance() {
        if (fitnessTechniqueWithSickJoints == null) {
            fitnessTechniqueWithSickJoints = new FitnessTechniqueForEachLetter();
        }
        return fitnessTechniqueWithSickJoints;
    }

    @Override
    public double calculateFitness(Dna<IMT_Gene> dna) {
        int correctCharactersCounter = 0;
        for (int i = 0; i < dna.getGene().getOwnPhrase().length(); i++) {
            if (i >= dna.getGene().getTargetPhrase().length()) correctCharactersCounter--;
            else if (dna.getGene().getOwnPhrase().charAt(i) == dna.getGene().getTargetPhrase().charAt(i)) {
                correctCharactersCounter++;
            }
        }
        return (double) correctCharactersCounter / dna.getGene().getTargetPhrase().length();
    }

    @Override
    public String toString() {
        return "Using: FitnessTechniqueForEachLetter";
    }
}
