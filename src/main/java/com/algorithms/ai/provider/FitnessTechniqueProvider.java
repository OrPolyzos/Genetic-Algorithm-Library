package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

/**
 * The interface Fitness technique provider.
 *
 * @param <T> the type parameter
 */
public interface FitnessTechniqueProvider<T> {

    /**
     * Provide fitness technique to calculate the fitness of a chromosome.
     *
     * @param chromosomeToCalculateFitness the chromosome to calculate fitness
     * @return the fitness of the chromosome
     */
    double provideFitnessTechnique(Chromosome<T> chromosomeToCalculateFitness);

}
