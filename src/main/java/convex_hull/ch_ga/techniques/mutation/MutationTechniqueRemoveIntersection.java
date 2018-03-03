package convex_hull.ch_ga.techniques.mutation;

import com.sun.corba.se.impl.oa.poa.POAImpl;
import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import ga.DNA;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;

import java.util.*;

public class MutationTechniqueRemoveIntersection implements MutationTechnique {

    private static MutationTechniqueRemoveIntersection mutationTechniqueRemoveIntersection;

    private MutationTechniqueRemoveIntersection() {
    }

    public static synchronized MutationTechniqueRemoveIntersection getInstance() {
        if (mutationTechniqueRemoveIntersection == null) {
            mutationTechniqueRemoveIntersection = new MutationTechniqueRemoveIntersection();
        }
        return mutationTechniqueRemoveIntersection;
    }

    @Override
    public DNA execute(DNA dnaToMutate) {
        Map<Integer,List<Point>> geneMap = dnaToMutate.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> mutatedHull = new ArrayList<>(geneMap.get(1));

        if (mutatedHull.size() > 3) {
            FitnessTechnique fitnessTechnique = dnaToMutate.getFitnessTechnique();

            int possibleIntersectionPoint = new Random().nextInt(mutatedHull.size() - 1);
            mutatedHull.remove(possibleIntersectionPoint);
            return new CH_DNA(points, mutatedHull, fitnessTechnique);
        }
        return dnaToMutate;
    }

    @Override
    public String toString() {
        return "Using: MutationTechniqueRemoveIntersection";
    }
}
