package com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.mutation;

import com.algorithm.genetic.ga.domain.Dna;
import com.algorithm.genetic.ga.techniques.MutationTechnique;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_Gene;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_Keyboard;

import java.util.Random;

public class MutationTechniqueReplaceWithRandomLetterFromKeyboard implements MutationTechnique<IMT_Gene> {

    private static MutationTechniqueReplaceWithRandomLetterFromKeyboard mutationTechniqueReplaceWithRandomLetterFromKeyboard;

    private MutationTechniqueReplaceWithRandomLetterFromKeyboard() {
    }

    public static synchronized MutationTechniqueReplaceWithRandomLetterFromKeyboard getInstance() {
        if (mutationTechniqueReplaceWithRandomLetterFromKeyboard == null) {
            mutationTechniqueReplaceWithRandomLetterFromKeyboard = new MutationTechniqueReplaceWithRandomLetterFromKeyboard();
        }
        return mutationTechniqueReplaceWithRandomLetterFromKeyboard;
    }

    @Override
    public void mutate(Dna<IMT_Gene> dnaToMutate) {
        IMT_Gene ch_gene = dnaToMutate.getGene();
        String ownPhrase = ch_gene.getOwnPhrase();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ownPhrase.length(); i++) {
            if (new Random().nextDouble() < 0.5) {
                stringBuilder.append(IMT_Keyboard.getRandomCharacterFromKeyboard());
            } else {
                stringBuilder.append(ownPhrase.charAt(i));
            }
        }
        ch_gene.setOwnPhrase(stringBuilder.toString());
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueReplaceWithRandomLetterFromKeyboard";
    }
}
