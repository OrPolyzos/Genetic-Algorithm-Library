package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

public interface MutationTechniqueProvider<T> {

    void provideMutationTechnique(Chromosome<T> chromosomeToMutate, double mutationRate);

}
