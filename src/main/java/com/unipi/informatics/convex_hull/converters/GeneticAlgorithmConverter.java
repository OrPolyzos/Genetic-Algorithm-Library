package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.dao.ChromosomeDao;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.dao.PointDao;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithmConverter {

    public static GeneticAlgorithmDao convertToGeneticAlgorithmDAO(GeneticAlgorithm<CH_Gene> geneticAlgorithm) {
        GeneticAlgorithmDao geneticAlgorithmDao = new GeneticAlgorithmDao();

        List<PointDao> pointDaos = new ArrayList<>();
        List<Point> points = geneticAlgorithm.getFittestChromosomeEver().getDna().getGene().getPoints();
        for (Point point : points) {
            pointDaos.add(PointConverter.convertToPointDAO(point, geneticAlgorithmDao));
        }
        geneticAlgorithmDao.setPoints(pointDaos);
        geneticAlgorithmDao.setPopulation(geneticAlgorithm.getPopulationCount());
        geneticAlgorithmDao.setMutationRate(geneticAlgorithm.getMutationRate());
        geneticAlgorithmDao.setGenerations(geneticAlgorithm.getGenerationsCounter());
        geneticAlgorithmDao.setDuration((convertDurationToSeconds(geneticAlgorithm.getDuration())));
        geneticAlgorithmDao.setPointsSize(geneticAlgorithm.getFittestChromosomeEver().getDna().getGene().getPoints().size());
        List<ChromosomeDao> chromosomeDaoList = new ArrayList<>();
        for (Chromosome<CH_Gene> chromosome : geneticAlgorithm.getFittestChromosomes()) {
            chromosomeDaoList.add(ChromosomeConverter.convertToChromosomeDAO(chromosome, geneticAlgorithmDao));
        }
        geneticAlgorithmDao.setFittestChromosomes(chromosomeDaoList);
        return geneticAlgorithmDao;
    }

    private static double convertDurationToSeconds(Duration duration) {
        final double thousandSeconds = 1000d;
        return duration.toMillis() / thousandSeconds;
    }

}
