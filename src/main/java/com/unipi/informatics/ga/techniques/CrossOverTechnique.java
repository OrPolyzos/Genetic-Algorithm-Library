package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.domain.Chromosome;

public interface CrossOverTechnique {
    Chromosome crossOver(Chromosome parentA, Chromosome parentB);
}