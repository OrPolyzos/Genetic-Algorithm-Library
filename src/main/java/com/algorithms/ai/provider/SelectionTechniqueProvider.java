package com.algorithms.ai.provider;

import com.algorithms.ai.techniques.SelectionTechnique;

import java.util.List;
import java.util.Random;

public class SelectionTechniqueProvider<T> {

    protected List<SelectionTechnique<T>> selectionTechniques;

    public SelectionTechniqueProvider(List<SelectionTechnique<T>> selectionTechniques) {
        this.selectionTechniques = selectionTechniques;
    }

    public SelectionTechnique<T> provideSelectionTechnique() {
        return selectionTechniques.get(new Random().nextInt(selectionTechniques.size()));
    }
}
