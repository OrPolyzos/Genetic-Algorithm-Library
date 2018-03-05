package com.unipi.informatics.ga.techniques;

import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Population;

public interface SelectionTechnique<T> {
    Chromosome<T> select(Population<T> population);
}
