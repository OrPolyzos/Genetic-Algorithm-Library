package com.algorithm.genetic.convex_hull.converters;

import com.algorithm.genetic.convex_hull.dao.CH_PointDao;
import com.algorithm.genetic.convex_hull.dao.ChromosomeDao;
import com.algorithm.genetic.convex_hull.dao.PointDao;

class ChromosomeConvexHullPointConverter {

    static CH_PointDao convertToChromosomeConvexHullPoint(ChromosomeDao chromosomeDao, PointDao pointDao) {
        CH_PointDao chromosomeConvexHullPoint = new CH_PointDao();
        chromosomeConvexHullPoint.setChromosomeDao(chromosomeDao);
        chromosomeConvexHullPoint.setPointDao(pointDao);
        return chromosomeConvexHullPoint;
    }
}
