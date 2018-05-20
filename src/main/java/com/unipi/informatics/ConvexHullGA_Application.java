package com.unipi.informatics;

import com.unipi.informatics.convex_hull.CH_Problem;
import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.services.GeneticAlgorithmService;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@ComponentScan("com.unipi.informatics.*")
public class ConvexHullGA_Application implements CommandLineRunner {

    @Autowired
    private GeneticAlgorithmService geneticAlgorithmService;

    public static void main(String[] args) {
        SpringApplication.run(ConvexHullGA_Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        int width = 500;
//        int height = 500;
//        int roundsPerTest = 50;
//        int startPoints = 100;
//        int maximumPoints = 2100;
//        double startMutationRate = 0.3d;
//        double maximumMutationRate = 1.1d;
//        int startPopulationNumber = 5;
//        int maxPopulationNumber = 101;
//
//        for (int points = startPoints; points < maximumPoints; points += 100) {
//            System.out.println("Testing with points: " + points);
//            for (double m = startMutationRate; m < maximumMutationRate; m += 0.05d) {
//                System.out.println("Testing with mutationRate: " + m);
//                for (int p = startPopulationNumber; p < maxPopulationNumber; p += 5) {
//                    System.out.println("Testing with population number: " + p);
//                    for (int r = 0; r < roundsPerTest; r++) {
//                        System.out.println("Mut: " + m + " | Pop: " + p + " | Points: " + points);
//                        GeneticAlgorithm<CH_Gene> geneticAlgorithm = new CH_Problem(width, height, m, p, points).solve();
//                        if (geneticAlgorithm.getFittestChromosomeEver().getFitness() < 1) {
//                            System.out.println("Happened");
//                        }
//                        else{
//                            GeneticAlgorithmDao geneticAlgorithmDao = geneticAlgorithmService.save(geneticAlgorithm);
//                        }
//                    }
//                }
//            }
//        }
    }
}
