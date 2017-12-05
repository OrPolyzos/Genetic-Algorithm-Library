package ga.techniques;

import ga.domain.DNA;

public interface MutationTechnique {
    DNA execute(DNA dnaToMutate);
}
