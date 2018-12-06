package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

public interface CrossOverTechniqueProvider<T> {

    Chromosome<T> provideCrossOverTechnique(Chromosome<T> firstParent, Chromosome<T> secondParent);

}
