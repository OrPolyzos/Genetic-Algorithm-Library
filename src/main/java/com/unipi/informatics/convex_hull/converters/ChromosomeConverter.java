package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.dao.CH_PointDao;
import com.unipi.informatics.convex_hull.dao.ChromosomeDao;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.dao.PointDao;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Chromosome;

import java.util.ArrayList;
import java.util.List;

class ChromosomeConverter {

    static ChromosomeDao convertToChromosomeDAO(Chromosome<CH_Gene> chromosome, GeneticAlgorithmDao geneticAlgorithmDao) {
        ChromosomeDao chromosomeDao = new ChromosomeDao();
        chromosomeDao.setGeneticAlgorithmDao(geneticAlgorithmDao);
        chromosomeDao.setFitness(chromosome.getFitness());

        List<Point> convexHull = chromosome.getDna().getGene().getConvexHull();
        List<CH_PointDao> chromosomeConvexHullPoints = new ArrayList<>();

        for (Point point : convexHull) {
            PointDao pointDao = PointConverter.convertToPointDAO(point, geneticAlgorithmDao);
            CH_PointDao ch_pointDao = (ChromosomeConvexHullPointConverter.convertToChromosomeConvexHullPoint(chromosomeDao, pointDao));

            chromosomeConvexHullPoints.add(ch_pointDao);
        }
        chromosomeDao.setCh_points(chromosomeConvexHullPoints);

        chromosomeDao.setFitnessTechnique(chromosome.getFitnessTechnique().toString());

        return chromosomeDao;
    }
}
