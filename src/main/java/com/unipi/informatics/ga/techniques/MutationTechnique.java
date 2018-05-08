package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.domain.Dna;

public interface MutationTechnique<T> {

    void mutate(Dna<T> dnaToMutate);
}
