package com.algorithm.genetic;

import com.algorithm.genetic.ga.domain.GeneticAlgorithm;
import com.algorithm.genetic.infinite_monkey_theorem.IMT_Problem;
import com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain.IMT_Gene;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.algorithm.genetic.*")
public class IMT_GeneticAlgorithmApplication {

    public static void main(String[] args) {
        GeneticAlgorithm<IMT_Gene> ga = new IMT_Problem("to be or not to be", 0.3, 500).solve();
    }

}

