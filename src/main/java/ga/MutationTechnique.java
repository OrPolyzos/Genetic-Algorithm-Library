package ga;

import domain.Point;

import java.util.List;

public interface MutationTechnique {
    List<Point> execute(DNA dnaToMutate);
}
