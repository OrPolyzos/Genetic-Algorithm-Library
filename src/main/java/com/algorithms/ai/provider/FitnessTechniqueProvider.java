package com.algorithms.ai.provider;

import com.algorithms.ai.techniques.FitnessTechnique;

import java.util.List;
import java.util.Random;

public class FitnessTechniqueProvider<T> {

    protected List<FitnessTechnique<T>> fitnessTechniques;

    public FitnessTechniqueProvider(List<FitnessTechnique<T>> fitnessTechniques) {
        this.fitnessTechniques = fitnessTechniques;
    }

    public FitnessTechnique<T> provideFitnessTechnique() {
        return fitnessTechniques.get(new Random().nextInt(fitnessTechniques.size()));
    }

}
