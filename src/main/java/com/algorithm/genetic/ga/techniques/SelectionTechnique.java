package com.algorithm.genetic.ga.techniques;

import com.algorithm.genetic.ga.domain.Chromosome;
import com.algorithm.genetic.ga.domain.Population;

public interface SelectionTechnique<T> {

    Chromosome<T> select(Population<T> population);
}
