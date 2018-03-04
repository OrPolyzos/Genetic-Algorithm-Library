package com.unipi.informatics;

import com.unipi.informatics.convex_hull.Convex_Hull_Problem;
import com.unipi.informatics.convex_hull.ch_ga.services.GeneticAlgorithmService;
import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.repositories.ChromosomeRepository;
import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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
        GeneticAlgorithm geneticAlgorithm = new Convex_Hull_Problem(0.03, 500, 500).solve();
        geneticAlgorithmService.save(geneticAlgorithm);
    }
}
