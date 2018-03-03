package convex_hull.utilities;

public class TimeIt {


    public static double code(Runnable block) {
        long start = System.nanoTime();
        try {
            block.run();
        } finally {
            long end = System.nanoTime();
            return (end - start) / 1.0e9;
        }
    }
}
