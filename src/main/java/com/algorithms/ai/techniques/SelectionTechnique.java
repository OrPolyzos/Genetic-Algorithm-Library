package com.algorithms.ai.techniques;

import com.algorithms.ai.domain.Chromosome;
import com.algorithms.ai.domain.Population;

public interface SelectionTechnique<T> {

    Chromosome<T> select(Population<T> population);
}
