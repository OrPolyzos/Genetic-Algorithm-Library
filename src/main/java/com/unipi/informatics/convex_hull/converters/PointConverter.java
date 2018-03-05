package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.dao.ChromosomeDAO;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDAO;
import com.unipi.informatics.convex_hull.dao.PointDAO;
import com.unipi.informatics.convex_hull.domain.Point;

class PointConverter {

    static PointDAO convertToPointDAO(Point point, GeneticAlgorithmDAO geneticAlgorithmDAO){
        PointDAO pointDAO = new PointDAO();
        pointDAO.setLabel(point.getLabel());
        pointDAO.setX(point.getX());
        pointDAO.setY(point.getY());
        pointDAO.setGeneticAlgorithmDAO(geneticAlgorithmDAO);
        return pointDAO;
    }
}
