package com.algorithm.genetic.library.ga.techniques;

import com.algorithm.genetic.library.ga.domain.Chromosome;
import com.algorithm.genetic.library.ga.domain.Population;

public interface SelectionTechnique<T> {

    Chromosome<T> select(Population<T> population);
}
