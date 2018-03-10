package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.dao.ChromosomeDao;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.dao.PointDao;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneticAlgorithmConverter {

    public static GeneticAlgorithmDao convertToGeneticAlgorithmDAO(GeneticAlgorithm<Map<Integer,List<Point>>> geneticAlgorithm){
        GeneticAlgorithmDao geneticAlgorithmDao = new GeneticAlgorithmDao();

        List<PointDao> pointDaos = new ArrayList<>();
        List<Point> points = geneticAlgorithm.getFittestChromosomeEver().getDna().getGene().get(0);
        for (Point point : points){
            pointDaos.add(PointConverter.convertToPointDAO(point, geneticAlgorithmDao));
        }
        geneticAlgorithmDao.setPoints(pointDaos);
        geneticAlgorithmDao.setPopulation(geneticAlgorithm.getPopulationCount());
        geneticAlgorithmDao.setMutationRate(geneticAlgorithm.getMutationRate());
        geneticAlgorithmDao.setGenerations(geneticAlgorithm.getGenerationsCounter());
        geneticAlgorithmDao.setDuration(geneticAlgorithm.getDuration() / 1000000000);
        List<ChromosomeDao> chromosomeDaoList = new ArrayList<>();
        for (Chromosome<Map<Integer,List<Point>>> chromosome : geneticAlgorithm.getFittestChromosomes()){
            chromosomeDaoList.add(ChromosomeConverter.convertToChromosomeDAO(chromosome, geneticAlgorithmDao));
        }
        geneticAlgorithmDao.setFittestChromosomes(chromosomeDaoList);
        return geneticAlgorithmDao;
    }

}
