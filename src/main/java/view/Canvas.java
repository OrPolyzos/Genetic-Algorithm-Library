package view;


import ga.domain.GeneticAlgorithm;

import javax.swing.*;
import java.awt.*;


public class Canvas extends JFrame {

    private static Canvas theCanvas;
    private JPanel jActionsPanel = new JActionsPanel();
    private CH_JInputPanel ch_jInputPanel = new CH_JInputPanel();
    private GeneticAlgorithm geneticAlgorithm;

    private Canvas() {
        super("New ConvexHull Problem");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, 9 * screenSize.height / 10);
        initializeComponents();
        this.setVisible(true);
    }

    public static Canvas getInstance() {
        if (theCanvas == null) {
            theCanvas = new Canvas();
        }
        return theCanvas;
    }

    public void initializeComponents() {
        getContentPane().removeAll();
        getContentPane().add(jActionsPanel, BorderLayout.SOUTH);
        getContentPane().add(ch_jInputPanel, BorderLayout.EAST);
    }

    public void setGeneticAlgorithm(GeneticAlgorithm geneticAlgorithm) {
        this.geneticAlgorithm = geneticAlgorithm;
    }

    public CH_JInputPanel getCh_jInputPanel() {
        return ch_jInputPanel;
    }
}
