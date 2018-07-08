package com.algorithm.genetic.convex_hull.ch_ga.techniques.crossover;

import com.algorithm.genetic.convex_hull.ch_ga.domain.CH_Gene;
import com.algorithm.genetic.convex_hull.domain.Point;
import com.algorithm.genetic.library.ga.domain.Chromosome;
import com.algorithm.genetic.library.ga.domain.Dna;
import com.algorithm.genetic.library.ga.techniques.CrossOverTechnique;
import com.algorithm.genetic.library.ga.techniques.FitnessTechnique;

import java.util.ArrayList;
import java.util.List;

public class CrossOverTechniqueElitism implements CrossOverTechnique<CH_Gene> {

    private static CrossOverTechniqueElitism crossOverTechniqueElitism;

    private CrossOverTechniqueElitism() {
    }

    public static synchronized CrossOverTechniqueElitism getInstance() {
        if (crossOverTechniqueElitism == null) {
            crossOverTechniqueElitism = new CrossOverTechniqueElitism();
        }
        return crossOverTechniqueElitism;
    }

    @Override
    public Chromosome<CH_Gene> crossOver(Chromosome<CH_Gene> parentA, Chromosome<CH_Gene> parentB) {
        Chromosome<CH_Gene> winnerParent;
        if (parentA.getFitness() > parentB.getFitness()) {
            winnerParent = parentA;
        } else {
            winnerParent = parentB;
        }
        List<Point> points = winnerParent.getDna().getGene().getPoints();
        List<Point> childHull = new ArrayList<>(winnerParent.getDna().getGene().getConvexHull());
        FitnessTechnique<CH_Gene> fitnessTechnique = winnerParent.getFitnessTechnique();
        CH_Gene childGene = new CH_Gene(points, childHull);
        Dna<CH_Gene> childDna = new Dna<>(childGene);
        return new Chromosome<>(childDna, fitnessTechnique);
    }

    @Override
    public String toString() {
        return "Using: CrossOverTechniqueHalfWay";
    }
}
