package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.dao.PointDao;
import com.unipi.informatics.convex_hull.domain.Point;

class PointConverter {

    static PointDao convertToPointDAO(Point point, GeneticAlgorithmDao geneticAlgorithmDao){
        PointDao pointDao = new PointDao();
        pointDao.setLabel(point.getLabel());
        pointDao.setX(point.getX());
        pointDao.setY(point.getY());
        pointDao.setGeneticAlgorithmDao(geneticAlgorithmDao);
        return pointDao;
    }
}
