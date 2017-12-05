package convex_hull;

import ga.Chromosome;
import ga.DNA;

public class CHChromosome extends Chromosome {

    public CHChromosome(DNA DNA) {
        super(DNA);
    }

    @Override
    public Chromosome getCopy() {
        return new CHChromosome(this.getDNA().getCopy());
    }

}
