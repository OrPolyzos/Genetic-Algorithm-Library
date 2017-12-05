package convex_hull;

import ga.Chromosome;
import ga.Population;

import java.util.List;
import java.util.stream.Collectors;


public class CHPopulation extends Population {

    public CHPopulation(List<Chromosome> chromosomes) {
        super(chromosomes);
    }

    @Override
    public Population getCopy() {
        return new CHPopulation(this.getChromosomes()
                .stream()
                .map(Chromosome::getCopy)
                .collect(Collectors.toList()));
    }
}
