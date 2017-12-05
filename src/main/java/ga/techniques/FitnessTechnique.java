package ga.techniques;

import ga.domain.DNA;

public interface FitnessTechnique {
    double calculateFitness(DNA dna);
}
