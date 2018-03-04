package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.DNA;

public interface MutationTechnique {
    DNA execute(DNA dnaToMutate);
}
