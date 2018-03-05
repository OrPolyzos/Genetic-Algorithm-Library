package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.domain.Chromosome;

public interface CrossOverTechnique<T> {
    Chromosome<T> crossOver(Chromosome<T> parentA, Chromosome<T> parentB);
}
