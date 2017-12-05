package convex_hull.fitness_techniques;

import ga.DNA;
import ga.FitnessTechnique;

public class FitnessTechniqueWithoutSickJoints implements FitnessTechnique {

    private static FitnessTechniqueWithoutSickJoints fitnessTechniqueWithoutSickJoints;

    private FitnessTechniqueWithoutSickJoints() {
    }

    public static synchronized FitnessTechniqueWithoutSickJoints getInstance() {
        if (fitnessTechniqueWithoutSickJoints == null) {
            fitnessTechniqueWithoutSickJoints = new FitnessTechniqueWithoutSickJoints();
        }
        return fitnessTechniqueWithoutSickJoints;
    }

    @Override
    public double calculateFitness(DNA dna) {
        if (dna.getGene().size() < 3) {
            return 0;
        }
        int fitOutsidePoints = dna.getOutsidePoints().size() + 1;
        int fitIntersections = dna.getIntersections() + 1;

        double fitness = 1 / (double) (fitOutsidePoints * fitIntersections);
        return fitness;
    }
}
