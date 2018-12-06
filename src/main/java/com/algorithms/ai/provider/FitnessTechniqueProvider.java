package com.algorithms.ai.provider;

import com.algorithms.ai.domain.Chromosome;

public interface FitnessTechniqueProvider<T> {

    double provideFitnessTechnique(Chromosome<T> chromosomeToCalculateFitness);

}
