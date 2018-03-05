package com.unipi.informatics.convex_hull.converters;

import com.unipi.informatics.convex_hull.dao.ChromosomeDAO;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDAO;
import com.unipi.informatics.convex_hull.dao.PointDAO;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneticAlgorithmConverter {

    public static GeneticAlgorithmDAO convertToGeneticAlgorithmDAO(GeneticAlgorithm<Map<Integer,List<Point>>> geneticAlgorithm){
        GeneticAlgorithmDAO geneticAlgorithmDAO = new GeneticAlgorithmDAO();

        List<PointDAO> pointDAOS = new ArrayList<>();
        List<Point> points = geneticAlgorithm.getFittestChromosomeEver().getDNA().getGene().get(0);
        for (Point point : points){
            pointDAOS.add(PointConverter.convertToPointDAO(point,geneticAlgorithmDAO));
        }
        geneticAlgorithmDAO.setPoints(pointDAOS);
        geneticAlgorithmDAO.setPopulation(geneticAlgorithm.getPopulationCount());
        geneticAlgorithmDAO.setMutationRate(geneticAlgorithm.getMutationRate());
        geneticAlgorithmDAO.setGenerations(geneticAlgorithm.getGenerationsCounter());
        geneticAlgorithmDAO.setDuration(geneticAlgorithm.getDuration() / 1000000000);
        List<ChromosomeDAO> chromosomeDAOList = new ArrayList<>();
        for (Chromosome<Map<Integer,List<Point>>> chromosome : geneticAlgorithm.getFittestChromosomes()){
            chromosomeDAOList.add(ChromosomeConverter.convertToChromosomeDAO(chromosome, geneticAlgorithmDAO));
        }
        geneticAlgorithmDAO.setFittestChromosomes(chromosomeDAOList);
        return geneticAlgorithmDAO;
    }

}
