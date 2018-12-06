package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

import java.util.List;

/**
 * The interface Selection technique provider.
 *
 * @param <T> the type parameter
 */
public interface SelectionTechniqueProvider<T> {

    /**
     * Provide the selection technique to pick a candidate parent from the current population.
     *
     * @param population            the population to choose from
     * @param fittestChromosomeEver the fittest chromosome ever
     * @param fittestChromosome     the fittest chromosome of the population
     * @return the chromosome
     */
    Chromosome<T> provideSelectionTechnique(List<Chromosome<T>> population, Chromosome<T> fittestChromosomeEver, Chromosome<T> fittestChromosome);
}
