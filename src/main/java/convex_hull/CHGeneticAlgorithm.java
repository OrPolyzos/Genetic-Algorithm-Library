package convex_hull;

import domain.Line;
import domain.Point;
import ga.*;
import utilities.MathUtilities;
import view.Canvas;
import view.JMessageComponent;
import view.JPolygonComponent;
import view.JStatsPanel;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CHGeneticAlgorithm extends GeneticAlgorithm {

    private Canvas canvas;
    private List<Point> points;

    public CHGeneticAlgorithm(List<Point> points,Canvas canvas, int populationCount, FitnessTechnique fitnessTechnique, double mutationRate, Map<Integer, MutationTechnique> mutationTechniqueMap) {
        super(populationCount, fitnessTechnique, mutationRate, mutationTechniqueMap);
        this.canvas = canvas;
        this.points = points;
    }


    @Override
    public void draw() {
        List<Point> convexHull = new ArrayList<>(this.getFittestChromosomeEver().getDNA().getGene());
//        List<Point> tod  = new ArrayList<>();
//        for (Point point : points) {
//            if (!MathUtilities.isInside(convexHull,point)){
//                tod.add(new Point(point));
//            }
//        }
//        List<JComponent> jComponentList = new ArrayList<>();
        JPolygonComponent jPolygon = new JPolygonComponent(convexHull,new ArrayList<>(points));
        jPolygon.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
//        jComponentList.add(jPolygon);
//
//        JMessageComponent jMessageComponent = new JMessageComponent("Generations: " + getGenerationsCounter(), canvas.getWidth()/2, canvas.getHeight()/2);
//        jMessageComponent.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
        canvas.getContentPane().removeAll();
        canvas.getContentPane().add(jPolygon, BorderLayout.CENTER);
//        canvas.getContentPane().add(jMessageComponent, BorderLayout.SOUTH);
//        canvas.getContentPane().add(jPoint, BorderLayout.CENTER);


        canvas.revalidate();
        canvas.repaint();
    }

    @Override
    public void initialGeneration() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            Set<Point> randomPoints = new LinkedHashSet<>();
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
            DNA DNA = new CHDNA(randomPoints, points, this.getMutationTechniqueMap(), this.getFitnessTechnique());
            Chromosome chromosome = new CHChromosome(DNA);
            chromosomes.add(chromosome);
        }
        this.setPopulation(new CHPopulation(chromosomes));
    }

    public void eliteGeneration() {
        List<Chromosome> chromosomes = new ArrayList<>();
        Chromosome fittestEver = this.getFittestChromosomeEver();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            DNA DNA = new CHDNA(new LinkedHashSet<>(fittestEver.getDNA().getGene()), new ArrayList<>(fittestEver.getDNA().getGene()), this.getMutationTechniqueMap(), this.getFitnessTechnique());
            Chromosome chromosome = new CHChromosome(DNA);
            chromosomes.add(chromosome);
        }
        this.setPopulation(new CHPopulation(chromosomes));
    }

    @Override
    public void nextGeneration() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
//            Chromosome parentA = this.getPopulation().pickOne().getCopy();
//            Chromosome parentB = this.getPopulation().pickOne().getCopy();
//            Chromosome child = crossOver(parentA,parentB);
//            Chromosome child = this.getPopulation().pickOne().getCopy();
            Chromosome child = this.getFittestChromosomeEver().getCopy();
            child.mutate(this.getMutationRate());
            chromosomes.add(child);
        }

        Population population = new CHPopulation(chromosomes);
        this.setPopulation(population);
        this.setGenerationsCounter(this.getGenerationsCounter() + 1);
    }

    @Override
    public Chromosome crossOver(Chromosome parentA, Chromosome parentB) {
        Set<Point> childHull = new LinkedHashSet<>();
        List<Point> parentAHull = parentA.getDNA().getGene();
        List<Point> parentBHull = parentB.getDNA().getGene();

        int lowerLimit = 0;
        if (parentAHull.size() < parentBHull.size()) {
            lowerLimit = parentAHull.size();
        } else {
            lowerLimit = parentBHull.size();
        }

        for (int i = 0; i < lowerLimit; i++) {
            if (new Random().nextDouble() < 0.5) {
                childHull.add(parentAHull.get(i));
            } else {
                childHull.add(parentBHull.get(i));
            }
        }

        DNA childDNA = new CHDNA(childHull, new ArrayList<>(points), this.getMutationTechniqueMap(), this.getFitnessTechnique());
        Chromosome chromosome = new CHChromosome(childDNA);
        return chromosome;
    }

}

