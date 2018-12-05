package com.algorithms.ai.provider;

import com.algorithms.ai.techniques.CrossOverTechnique;

import java.util.List;
import java.util.Random;

public class CrossOverTechniqueProvider<T> {

    protected List<CrossOverTechnique<T>> crossOverTechniques;

    public CrossOverTechniqueProvider(List<CrossOverTechnique<T>> crossOverTechniques) {
        this.crossOverTechniques = crossOverTechniques;
    }

    public CrossOverTechnique<T> provideCrossOverTechnique() {
        return crossOverTechniques.get(new Random().nextInt(crossOverTechniques.size()));
    }

}
