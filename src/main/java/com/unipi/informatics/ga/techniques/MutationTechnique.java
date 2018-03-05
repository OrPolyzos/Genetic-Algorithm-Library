package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.DNA;

public interface MutationTechnique<T> {
    DNA<T> execute(DNA<T> dnaToMutate);
}
