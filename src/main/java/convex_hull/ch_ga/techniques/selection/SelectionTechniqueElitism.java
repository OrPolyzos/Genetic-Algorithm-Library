package convex_hull.ch_ga.techniques.selection;

import ga.domain.Chromosome;
import ga.domain.Population;
import ga.techniques.SelectionTechnique;

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
