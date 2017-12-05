package convex_hull.mutation_techniques;

import domain.Point;
import ga.DNA;
import ga.MutationTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationTechniqueRemoveSickJoint implements MutationTechnique {

    @Override
    public List<Point> execute(DNA dnaToMutate) {
        List<Point> mutatedHull = new ArrayList<>(dnaToMutate.getGene());
        List<Point> sickJoints = dnaToMutate.getSickJoints();
        mutatedHull.removeAll(sickJoints);
//        mutatedHull.remove(new Random().nextInt(mutatedHull.size()));
//        if (!sickJoints.isEmpty()) {
//                Point sickPoint = sickJoints.get(new Random().nextInt(sickJoints.size()));
//                for (int i = mutatedHull.size()-1; i>=0; i--){
//                    if (mutatedHull.get(i).equals(sickPoint)){
//                        mutatedHull.remove(i);
//                        break;
//                    }
//                }
////                mutatedHull.remove(sickPoint);
//        }
        return mutatedHull;
    }

}
