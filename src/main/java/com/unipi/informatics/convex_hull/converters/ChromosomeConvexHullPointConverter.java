package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.dao.CH_PointDao;
import com.unipi.informatics.convex_hull.dao.ChromosomeDao;
import com.unipi.informatics.convex_hull.dao.PointDao;

class ChromosomeConvexHullPointConverter {

    static CH_PointDao convertToChromosomeConvexHullPoint(ChromosomeDao chromosomeDao, PointDao pointDao) {
        CH_PointDao chromosomeConvexHullPoint = new CH_PointDao();
        chromosomeConvexHullPoint.setChromosomeDao(chromosomeDao);
        chromosomeConvexHullPoint.setPointDao(pointDao);
        return chromosomeConvexHullPoint;
    }
}
