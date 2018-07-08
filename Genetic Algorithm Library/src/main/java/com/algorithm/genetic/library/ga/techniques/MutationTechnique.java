package com.algorithm.genetic.library.ga.techniques;

import com.algorithm.genetic.library.ga.domain.Dna;

public interface MutationTechnique<T> {

    void mutate(Dna<T> dnaToMutate);
}
