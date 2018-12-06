package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

import java.util.List;

/**
 * The interface Initial generation provider.
 *
 * @param <T> the type parameter
 */
public interface InitialGenerationProvider<T> {

    /**
     * Provide the technique to generate the initial generation.
     *
     * @return the initial generation
     */
    List<Chromosome<T>> provideInitialGeneration();
}
