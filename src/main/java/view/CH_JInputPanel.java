package view;

import javax.swing.*;
import java.awt.*;

public class CH_JInputPanel extends JPanel {

    private JSpinner mutationRateSpinner;
    private JSpinner populationCountSpinner;
    private JSpinner pointsCountSpinner;

    public CH_JInputPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel miniPanelGeneticAlgorithm = new JPanel();
        miniPanelGeneticAlgorithm.setLayout(new FlowLayout());
        JLabel miniPanelGeneticAlgorithmTitle = new JLabel("Genetic Algorithm");
        miniPanelGeneticAlgorithmTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        miniPanelGeneticAlgorithm.add(miniPanelGeneticAlgorithmTitle);
        this.add(miniPanelGeneticAlgorithm);

        JLabel mutationRateLabel = new JLabel("Mutation Rate: ");
        SpinnerModel mutationRateModel = new SpinnerNumberModel(0.3, 0.05, 1, 0.05);
        mutationRateSpinner = new JSpinner(mutationRateModel);
        Component mutationRateSpinnerEditor = mutationRateSpinner.getEditor();
        JFormattedTextField mutationRateSpinnerEditorTextField = ((JSpinner.DefaultEditor) mutationRateSpinnerEditor).getTextField();
        mutationRateSpinnerEditorTextField.setColumns(2);
        mutationRateSpinnerEditorTextField.setEditable(false);

        JPanel mutationRatePanel = new JPanel();
        mutationRatePanel.setLayout(new FlowLayout());
        mutationRatePanel.add(mutationRateLabel);
        mutationRatePanel.add(mutationRateSpinner);
        this.add(mutationRatePanel);

        JLabel populationCountLabel = new JLabel("Population: ");
        SpinnerModel populationCountModel = new SpinnerNumberModel(500, 10, 5000, 10);
        populationCountSpinner = new JSpinner(populationCountModel);
        Component populationCountSpinnerEditor = populationCountSpinner.getEditor();
        JFormattedTextField populationCountSpinnerEditorTextField = ((JSpinner.DefaultEditor) populationCountSpinnerEditor).getTextField();
        populationCountSpinnerEditorTextField.setColumns(4);
        populationCountSpinnerEditorTextField.setEditable(false);

        JPanel populationCountPanel = new JPanel();
        populationCountPanel.setLayout(new FlowLayout());
        populationCountPanel.add(populationCountLabel, BorderLayout.WEST);
        populationCountPanel.add(populationCountSpinner, BorderLayout.EAST);
        this.add(populationCountPanel);


        //Convex Hull variables
        JPanel miniPanelConvexHull = new JPanel();
        miniPanelConvexHull.setLayout(new FlowLayout());
        JLabel miniPanelConvexHullTitle = new JLabel("Convex Hull Problem");
        miniPanelConvexHullTitle.setHorizontalTextPosition(SwingConstants.CENTER);
        miniPanelConvexHull.add(miniPanelConvexHullTitle);
        this.add(miniPanelConvexHull);

        JLabel pointsCountLabel = new JLabel("Points: ");
        SpinnerModel pointsCountModel = new SpinnerNumberModel(500, 3, 20000, 50);
        pointsCountSpinner = new JSpinner(pointsCountModel);
        Component pointsCountSpinnerEditor = pointsCountSpinner.getEditor();
        JFormattedTextField pointsCountSpinnerEditorTextField = ((JSpinner.DefaultEditor) pointsCountSpinnerEditor).getTextField();
        pointsCountSpinnerEditorTextField.setColumns(4);
        pointsCountSpinnerEditorTextField.setEditable(false);

        JPanel pointsCountPanel = new JPanel();
        pointsCountPanel.setLayout(new FlowLayout());
        pointsCountPanel.add(pointsCountLabel, BorderLayout.WEST);
        pointsCountPanel.add(pointsCountSpinner, BorderLayout.EAST);
        this.add(pointsCountPanel);
    }

    public int getPointsCount() {
        return (int) pointsCountSpinner.getValue();
    }

    public int getPopulationCount() {
        return (int) populationCountSpinner.getValue();
    }

    public double getMutationRate() {
        return (double) mutationRateSpinner.getValue();
    }

    public void toggleEverything() {
        mutationRateSpinner.setEnabled(!mutationRateSpinner.isEnabled());
        populationCountSpinner.setEnabled(!populationCountSpinner.isEnabled());
        pointsCountSpinner.setEnabled(!pointsCountSpinner.isEnabled());
    }

}
