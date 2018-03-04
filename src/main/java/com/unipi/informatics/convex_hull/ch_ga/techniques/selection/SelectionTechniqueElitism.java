package com.unipi.informatics.convex_hull.ch_ga.techniques.selection;

import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Population;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

public class SelectionTechniqueElitism implements SelectionTechnique {

    private static SelectionTechniqueElitism selectionTechniqueElitism;

    private SelectionTechniqueElitism() {
    }

    public static synchronized SelectionTechniqueElitism getInstance() {
        if (selectionTechniqueElitism == null) {
            selectionTechniqueElitism = new SelectionTechniqueElitism();
        }
        return selectionTechniqueElitism;
    }

    @Override
    public Chromosome select(Population population) {
        return population.getFittestChromosome().getCopy();
    }

    @Override
    public String toString() {
        return "Using: SelectionTechniqueElitism";
    }
}
