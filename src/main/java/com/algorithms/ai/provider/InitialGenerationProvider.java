package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

import java.util.List;

public interface InitialGenerationProvider<T> {

    List<Chromosome<T>> provideInitialGeneration();
}
