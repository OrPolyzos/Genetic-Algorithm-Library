package convex_hull.ch_ga.techniques.fitness;

import ga.domain.DNA;
import ga.techniques.FitnessTechnique;

public class FitnessTechniqueWithSickJoints implements FitnessTechnique {

    private static FitnessTechniqueWithSickJoints fitnessTechniqueWithSickJoints;

    private FitnessTechniqueWithSickJoints() {
    }

    public static synchronized FitnessTechniqueWithSickJoints getInstance() {
        if (fitnessTechniqueWithSickJoints == null) {
            fitnessTechniqueWithSickJoints = new FitnessTechniqueWithSickJoints();
        }
        return fitnessTechniqueWithSickJoints;
    }

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
