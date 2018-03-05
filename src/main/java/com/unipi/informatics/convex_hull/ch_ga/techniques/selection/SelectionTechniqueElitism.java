package com.unipi.informatics.convex_hull.ch_ga.techniques.selection;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Population;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.List;
import java.util.Map;

public class SelectionTechniqueElitism implements SelectionTechnique<Map<Integer,List<Point>>> {

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
    public Chromosome<Map<Integer,List<Point>>> select(Population<Map<Integer,List<Point>>> population) {
        return population.getFittestChromosome().getCopy();
    }

    @Override
    public String toString() {
        return "Using: SelectionTechniqueElitism";
    }
}
