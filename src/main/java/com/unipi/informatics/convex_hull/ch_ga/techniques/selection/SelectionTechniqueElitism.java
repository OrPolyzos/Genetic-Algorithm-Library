package com.unipi.informatics.convex_hull.ch_ga.techniques.selection;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Population;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

public class SelectionTechniqueElitism implements SelectionTechnique<CH_Gene> {

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
    public Chromosome<CH_Gene> select(Population<CH_Gene> population) {
        return population.getFittestChromosome();
    }

    @Override
    public String toString() {
        return "Using: SelectionTechniqueElitism";
    }
}
