package com.algorithms.ai.techniques;

import com.algorithms.ai.domain.Chromosome;

import java.util.List;

public interface SelectionTechnique<T> {

    Chromosome<T> select(List<Chromosome<T>> population, Chromosome<T> fittestChromosomeEver, Chromosome<T> fittestChromosome);
}
