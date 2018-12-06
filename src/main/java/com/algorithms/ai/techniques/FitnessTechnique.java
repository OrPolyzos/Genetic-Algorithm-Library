package com.algorithms.ai.techniques;

public interface FitnessTechnique<T> {

    double calculateFitness(T gene);
}
