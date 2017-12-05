package convex_hull.fitness_techniques;

import ga.DNA;
import ga.FitnessTechnique;

public class FitnessTechniqueWithSickJoints implements FitnessTechnique {

    @Override
    public double calculateFitness(DNA dna) {
        if (dna.getGene().size() < 3) {
            return 0;
        }
        int fitOutsidePoints = dna.getOutsidePoints().size() + 1;
        int fitSickJoints = dna.getSickJoints().size() + 1;
        int fitIntersections = dna.getIntersections() + 1;

        double fitness = 1 / (double) (fitIntersections * fitOutsidePoints * fitSickJoints);
        return fitness;
    }
}
