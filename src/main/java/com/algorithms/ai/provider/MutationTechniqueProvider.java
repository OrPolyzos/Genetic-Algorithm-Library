package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

/**
 * The interface Mutation technique provider.
 *
 * @param <T> the type parameter
 */
public interface MutationTechniqueProvider<T> {

    /**
     * Provide the mutation technique to mutate a chromosome.
     *
     * @param chromosomeToMutate the chromosome to mutate
     * @param mutationRate       the mutation rate
     */
    void provideMutationTechnique(Chromosome<T> chromosomeToMutate, double mutationRate);

}
