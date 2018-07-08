package com.algorithm.genetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.algorithm.genetic.*")
public class CH_GeneticAlgorithmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CH_GeneticAlgorithmApplication.class, args);
    }

}

