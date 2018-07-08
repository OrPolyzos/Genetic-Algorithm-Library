package com.algorithm.genetic.ga.techniques;

import com.algorithm.genetic.ga.domain.Dna;

public interface MutationTechnique<T> {

    void mutate(Dna<T> dnaToMutate);
}
