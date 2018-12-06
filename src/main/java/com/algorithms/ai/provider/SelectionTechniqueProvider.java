package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

import java.util.List;

public interface SelectionTechniqueProvider<T> {

    Chromosome<T> provideSelectionTechnique(List<Chromosome<T>> population, Chromosome<T> fittestChromosomeEver, Chromosome<T> fittestChromosome);
}
