package com.unipi.informatics.convex_hull.web.controllers;

import com.unipi.informatics.convex_hull.CH_Problem;
import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.services.GeneticAlgorithmService;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {

    @Autowired
    private GeneticAlgorithmService geneticAlgorithmService;

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public GeneticAlgorithmDao run(@RequestParam int width, @RequestParam int height, @RequestParam double mutationRate, @RequestParam int populationCount, @RequestParam int pointsCount) throws Exception {
        CH_Problem CH_problem = new CH_Problem(width, height, mutationRate, populationCount, pointsCount);
        GeneticAlgorithm<CH_Gene> geneticAlgorithm = CH_problem.solve();
        return GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm);
    }

    public GeneticAlgorithmDao run(String... args) throws Exception {
        int width = 500;
        int height = 500;
        int roundsPerTest = 50;
        int startPoints = 100;
        int maximumPoints = 2100;
        double startMutationRate = 0.3d;
        double maximumMutationRate = 1.1d;
        int startPopulationNumber = 5;
        int maxPopulationNumber = 1001;

        for (int points = startPoints; points < maximumPoints; points += 100) {
            System.out.println("Testing with points: " + points);
            for (double m = startMutationRate; m < maximumMutationRate; m += 0.05d) {
                System.out.println("Testing with mutationRate: " + m);
                for (int p = startPopulationNumber; p < maxPopulationNumber; p += 50) {
                    System.out.println("Testing with population number: " + p);
                    for (int r = 0; r < roundsPerTest; r++) {
                        System.out.println("Mut: " + m + " | Pop: " + p + " | Points: " + points);
                        GeneticAlgorithm<CH_Gene> geneticAlgorithm = new CH_Problem(width, height, m, p, points).solve();
                        if (geneticAlgorithm.getFittestChromosomeEver().getFitness() < 1) {
                            System.out.println("Happened");
                            GeneticAlgorithmDao dao = GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm);
                            return dao;
                        }
                    }
                }
            }
        }
        return null;
    }

}
