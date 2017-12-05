package convex_hull.ch_ga.domain;

import ga.domain.Chromosome;
import ga.domain.GeneticAlgorithm;
import ga.domain.Population;

import java.util.List;

public class CH_Population extends Population {

    public CH_Population(List<Chromosome> chromosomes, GeneticAlgorithm geneticAlgorithm) {
        super(chromosomes, geneticAlgorithm);
    }

}
