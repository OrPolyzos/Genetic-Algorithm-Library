package view;

import convex_hull.ch_ga.domain.CH_GeneticAlgorithm;

import javax.swing.*;
import java.awt.*;

public class JStatsPanel extends JPanel {

    private CH_GeneticAlgorithm geneticAlgorithm;

    public JStatsPanel(CH_GeneticAlgorithm geneticAlgorithm) {
        super();
        this.geneticAlgorithm = geneticAlgorithm;
        this.setLayout(new FlowLayout());
        JLabel generationsCounter = new JLabel("Generations: " + geneticAlgorithm.getGenerationsCounter() + " | ");
        this.add(generationsCounter);
        JLabel elapsedTime = new JLabel("Elapsed Time: " + (System.nanoTime() - geneticAlgorithm.getStartTime()) / 1.0e9 + " seconds");
        this.add(elapsedTime);
    }


}
