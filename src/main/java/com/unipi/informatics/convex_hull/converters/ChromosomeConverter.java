package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.dao.ChromosomeConvexHullPoint;
import com.unipi.informatics.convex_hull.dao.ChromosomeDAO;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDAO;
import com.unipi.informatics.convex_hull.dao.PointDAO;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Chromosome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ChromosomeConverter {

    static ChromosomeDAO convertToChromosomeDAO(Chromosome<Map<Integer, List<Point>>> chromosome, GeneticAlgorithmDAO geneticAlgorithmDAO) {
        ChromosomeDAO chromosomeDAO = new ChromosomeDAO();
        chromosomeDAO.setGeneticAlgorithmDAO(geneticAlgorithmDAO);
        chromosomeDAO.setFitness(chromosome.getFitness());

        List<Point> convexHull = chromosome.getDNA().getGene().get(1);
        List<ChromosomeConvexHullPoint> chromosomeConvexHullPoints = new ArrayList<>();

        for (Point point : convexHull) {
            PointDAO pointDAO = PointConverter.convertToPointDAO(point, geneticAlgorithmDAO);
            ChromosomeConvexHullPoint c = (ChromosomeConvexHullPointConverter.convertToChromosomeConvexHullPoint(chromosomeDAO, pointDAO));

            chromosomeConvexHullPoints.add(c);
        }
        chromosomeDAO.setChromosomeConvexHullPoints(chromosomeConvexHullPoints);

        chromosomeDAO.setFitnessTechnique(chromosome.getFitnessTechnique().toString());

        return chromosomeDAO;
    }
}
