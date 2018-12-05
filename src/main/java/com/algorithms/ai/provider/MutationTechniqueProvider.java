package com.algorithms.ai.provider;

import com.algorithms.ai.techniques.MutationTechnique;

import java.util.List;
import java.util.Random;

public class MutationTechniqueProvider<T> {

    protected List<MutationTechnique<T>> mutationTechniques;

    public MutationTechniqueProvider(List<MutationTechnique<T>> mutationTechniques) {
        this.mutationTechniques = mutationTechniques;
    }

    public MutationTechnique<T> provideMutationTechnique() {
        return mutationTechniques.get(new Random().nextInt(mutationTechniques.size()));
    }

}
