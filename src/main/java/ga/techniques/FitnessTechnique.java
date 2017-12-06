package ga.techniques;

import ga.DNA;

public interface FitnessTechnique {
    double calculateFitness(DNA dna);
}
