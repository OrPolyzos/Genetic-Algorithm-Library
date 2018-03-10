package com.unipi.informatics;

import com.unipi.informatics.convex_hull.Convex_Hull_Problem;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.services.GeneticAlgorithmService;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;
import java.util.Random;

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

        int limit = 1000;
        int pointsCount = 10000;
        for (int i = 0; i < limit; i++) {
            double mutationRate;
            do {
                mutationRate = new Random().nextDouble();
            } while (mutationRate == 0 || mutationRate > 0.5);
            int populationCount;
            do {
                populationCount = new Random().nextInt(50);
            } while (populationCount < 5);

            System.out.println();
            System.out.println("Iteration " + i);
            System.out.println("Points: " + pointsCount);
            System.out.println("MutationRate " + mutationRate);
            System.out.println("PopulationCount " + populationCount);
            GeneticAlgorithm<Map<Integer, List<Point>>> geneticAlgorithm = new Convex_Hull_Problem(500, 500, mutationRate, populationCount, pointsCount).solve();
            System.out.println("Duration " + geneticAlgorithm.getDuration());
            System.out.println("Generations " + geneticAlgorithm.getGenerationsCounter());
            System.out.println();

            geneticAlgorithmService.save(geneticAlgorithm);


        }

    }
}
