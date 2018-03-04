package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.convex_hull.ch_ga.CH_DNA;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.ConvexHullUtilities;
import com.unipi.informatics.ga.DNA;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import com.unipi.informatics.ga.domain.Population;
import com.unipi.informatics.ga.techniques.CrossOverTechnique;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CH_GeneticAlgorithm extends GeneticAlgorithm {

    private List<Point> points;


    public CH_GeneticAlgorithm(List<Point> points, int populationCount, double mutationRate, FitnessTechnique fitnessTechnique, SelectionTechnique selectionTechnique, CrossOverTechnique crossOverTechnique, Map<Integer, MutationTechnique> mutationTechniqueMap) {
        super(populationCount, mutationRate, fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        this.points = points;
    }


    public void initialGeneration() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            List<Point> randomConvexHull = ConvexHullUtilities.getRandomPoints(points, 3);
            DNA DNA = new CH_DNA(points, randomConvexHull);
            Chromosome chromosome = new Chromosome(DNA, getFitnessTechnique());
            chromosomes.add(chromosome);
        }
        Population initialPopulation = new Population(chromosomes);
        this.setPopulation(initialPopulation);
    }

    public void eliteGeneration() {
        DNA bestDNA = getFittestChromosomeEver().getDNA();
        Map<Integer, List<Point>> geneMap = bestDNA.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> poorConvexHullPoints = new ArrayList<>(geneMap.get(1));
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            DNA DNA = new CH_DNA(points, poorConvexHullPoints);
            Chromosome chromosome = new Chromosome(DNA, getFitnessTechnique());
            chromosomes.add(chromosome);
        }
        Population elitePopulation = new Population(chromosomes);
        this.setPopulation(elitePopulation);

    }

    @Override
    public void draw() {
        System.out.println(toString());
    }

    @Override
    public void findFittestChromosomeEver() {

        Chromosome bestChromosomeEver = this.getFittestChromosomeEver();
        double bestFitnessEver = this.getBestFitnessEver();
        Chromosome bestPopulationChromosome = this.getPopulation().getFittestChromosome();
        double bestPopulationFitness = bestPopulationChromosome.getFitness();

        if (bestPopulationFitness > bestFitnessEver) {
            this.setBestFitnessEver(bestPopulationFitness);
            this.setFittestChromosomeEver(bestPopulationChromosome);
        } else if (bestPopulationFitness == bestFitnessEver) {
            if (bestPopulationChromosome.getDNA().getGene().get(2).size() == bestChromosomeEver.getDNA().getGene().get(2).size()) {
                if (bestPopulationChromosome.getDNA().getGene().size() < bestChromosomeEver.getDNA().getGene().size()) {
                    this.setBestFitnessEver(bestPopulationFitness);
                    this.setFittestChromosomeEver(bestPopulationChromosome);
                }
            }
        }

    }

//    protected void paintComponent(Graphics graphics) {
//        super.paintComponent(graphics);
//        Graphics2D graphics2D = (Graphics2D) graphics.create();
//
//          /* Enable anti-aliasing and pure stroke */
//        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//
//        Path2D path = new Path2D.Double();
//        List<Point> convexHull = this.getFittestChromosomeEver().getDNA().getGene();
//        path.moveTo(convexHull.get(0).getX(), convexHull.get(0).getY());
//        for (int i = 1; i < convexHull.size(); ++i) {
//            path.lineTo(convexHull.get(i).getX(), convexHull.get(i).getY());
//        }
//        path.closePath();
//        graphics2D.setColor(Color.BLACK);
//        graphics2D.draw(path);
//
//
//        List<Point> points = this.getFittestChromosomeEver().getDNA().getPoints();
//        graphics2D.setColor(Color.RED);
//        for (Point point : points) {
//            Ellipse2D.Double shape = new Ellipse2D.Double(point.getX() - 1.25, point.getY() - 1.25, 2.5, 2.5);
//            graphics2D.fill(shape);
//        }
//    }

    @Override
    public String toString() {
        StringBuilder geneticAlgorithmBuilder = new StringBuilder();
        geneticAlgorithmBuilder.append("Generation: " + getGenerationsCounter()).append("\n")
                .append("Outside Points: " + getFittestChromosomeEver().getDNA().getGene().get(2).size()).append("\n")
                .append("Sick Joints: " + getFittestChromosomeEver().getDNA().getGene().get(3).size()).append("\n")
                .append("Intersections: " + getFittestChromosomeEver().getDNA().getIntersections()).append("\n")
                .append("Convex points: " + getFittestChromosomeEver().getDNA().getGene().get(1).size()).append("\n");
        return geneticAlgorithmBuilder.toString();
    }
}