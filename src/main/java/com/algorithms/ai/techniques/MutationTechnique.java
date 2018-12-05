package com.algorithms.ai.techniques;

import com.algorithms.ai.domain.Dna;

public interface MutationTechnique<T> {

    void mutate(Dna<T> dnaToMutate);
}
