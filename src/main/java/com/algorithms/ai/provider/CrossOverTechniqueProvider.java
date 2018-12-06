package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

/**
 * The interface Cross over technique provider.
 *
 * @param <T> the type parameter
 */
public interface CrossOverTechniqueProvider<T> {

    /**
     * Provide the cross over technique to combine two parent chromosomes and form a child chromosome.
     *
     * @param firstParent  the first parent
     * @param secondParent the second parent
     * @return the child chromosome
     */
    Chromosome<T> provideCrossOverTechnique(Chromosome<T> firstParent, Chromosome<T> secondParent);

}
