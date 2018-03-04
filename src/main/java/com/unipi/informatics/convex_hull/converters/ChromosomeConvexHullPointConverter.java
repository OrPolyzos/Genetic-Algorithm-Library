package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.dao.ChromosomeConvexHullPoint;
import com.unipi.informatics.convex_hull.dao.ChromosomeDAO;
import com.unipi.informatics.convex_hull.dao.PointDAO;

public class ChromosomeConvexHullPointConverter {

    public static ChromosomeConvexHullPoint convertToChromosomeConvexHullPoint(ChromosomeDAO chromosomeDAO, PointDAO pointDAO) {
        ChromosomeConvexHullPoint chromosomeConvexHullPoint = new ChromosomeConvexHullPoint();
        chromosomeConvexHullPoint.setChromosomeDAO(chromosomeDAO);
        chromosomeConvexHullPoint.setPointDAO(pointDAO);
        return chromosomeConvexHullPoint;
    }

}
