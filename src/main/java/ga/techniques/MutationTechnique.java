package ga.techniques;

import ga.DNA;

public interface MutationTechnique {
    DNA execute(DNA dnaToMutate);
}
