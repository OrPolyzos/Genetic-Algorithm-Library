package convex_hull.ch_ga.techniques.selection;

import ga.domain.Chromosome;
import ga.domain.Population;
import ga.techniques.SelectionTechnique;

import java.util.List;
import java.util.Random;

public class SelectionTechniqueRouletteWheel implements SelectionTechnique {

    private static SelectionTechniqueRouletteWheel selectionTechniqueRouletteWheel;

    private SelectionTechniqueRouletteWheel() {
    }

    public static synchronized SelectionTechniqueRouletteWheel getInstance() {
        if (selectionTechniqueRouletteWheel == null) {
            selectionTechniqueRouletteWheel = new SelectionTechniqueRouletteWheel();
        }
        return selectionTechniqueRouletteWheel;
    }

    @Override
    public Chromosome select(Population population) {
        List<Chromosome> chromosomes = population.getChromosomes();
        int index = 0;
        double r = new Random().nextDouble();
        while (r > 0) {
            r = r - chromosomes.get(index).getProbability();
            index++;
        }
        index--;
        return chromosomes.get(index).getCopy();
    }

    @Override
    public String toString() {
        return "Using: SelectionTechniqueRouletteWheel";
    }
}
