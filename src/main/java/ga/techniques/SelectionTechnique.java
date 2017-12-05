package ga.techniques;

import ga.domain.Chromosome;
import ga.domain.Population;

public interface SelectionTechnique {

    Chromosome select(Population population);
}
