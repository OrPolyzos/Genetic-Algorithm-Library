package com.algorithms.ai.techniques;

import com.algorithms.ai.domain.Chromosome;

public interface CrossOverTechnique<T> {

    Chromosome<T> crossOver(Chromosome<T> parentA, Chromosome<T> parentB);
}
