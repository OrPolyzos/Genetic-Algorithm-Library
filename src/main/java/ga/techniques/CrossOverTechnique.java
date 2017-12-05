package ga.techniques;

import ga.domain.Chromosome;

public interface CrossOverTechnique {
    Chromosome crossOver(Chromosome parentA, Chromosome parentB);
}
