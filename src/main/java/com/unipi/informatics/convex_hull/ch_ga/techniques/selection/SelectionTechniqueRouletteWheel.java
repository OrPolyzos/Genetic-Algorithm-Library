package com.unipi.informatics.convex_hull.ch_ga.techniques.selection;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Population;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.List;
import java.util.Random;

public class SelectionTechniqueRouletteWheel implements SelectionTechnique<CH_Gene> {

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
    public Chromosome<CH_Gene> select(Population<CH_Gene> population) {
        List<Chromosome<CH_Gene>> chromosomes = population.getChromosomes();
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
