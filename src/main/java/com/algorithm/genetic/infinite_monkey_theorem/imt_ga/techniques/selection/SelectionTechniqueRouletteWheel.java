package com.algorithm.genetic.infinite_monkey_theorem.imt_ga.techniques.selection;

import com.algorithm.genetic.library.ga.domain.Chromosome;
import com.algorithm.genetic.library.ga.domain.Population;
import com.algorithm.genetic.library.ga.techniques.SelectionTechnique;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_Gene;

import java.util.List;
import java.util.Random;

public class SelectionTechniqueRouletteWheel implements SelectionTechnique<IMT_Gene> {

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
    public Chromosome<IMT_Gene> select(Population<IMT_Gene> population) {
        List<Chromosome<IMT_Gene>> chromosomes = population.getChromosomes();
        int index = 0;
        double r = new Random().nextDouble();
        while (r > 0) {
            r = r - chromosomes.get(index).getProbability();
            index++;
        }
        index--;
        return chromosomes.get(index);
    }

    @Override
    public String toString() {
        return "Using: SelectionTechniqueRouletteWheel";
    }
}
