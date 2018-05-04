package com.unipi.informatics;

import com.unipi.informatics.convex_hull.services.GeneticAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.unipi.informatics.*")
public class ConvexHullGA_Application {//implements CommandLineRunner {
    @Autowired
    private GeneticAlgorithmService geneticAlgorithmService;

    public static void main(String[] args) {
        SpringApplication.run(ConvexHullGA_Application.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        int limit = 5000;
//        int pointsCount = 10000;
//        for (int i = 0; i < limit; i++) {
//            double mutationRate;
//            do {
//                mutationRate = new Random().nextDouble();
//            } while (mutationRate == 0);
//            int populationCount;
//            do {
//                populationCount = new Random().nextInt(250);
//            } while (populationCount < 5);
//
//            System.out.println();
//            System.out.println("Iteration " + i);
//            System.out.println("Points: " + pointsCount);
//            System.out.println("MutationRate " + mutationRate);
//            System.out.println("PopulationCount " + populationCount);
//            GeneticAlgorithm<Map<Integer, List<Point>>> geneticAlgorithm = new CH_Problem(500, 500, mutationRate, populationCount, pointsCount).solve();
//            System.out.println("Duration " + geneticAlgorithm.getDuration());
//            System.out.println("Generations " + geneticAlgorithm.getGenerationsCounter());
//            System.out.println();
//
//            geneticAlgorithmService.save(geneticAlgorithm);
//        }
//    }
}
