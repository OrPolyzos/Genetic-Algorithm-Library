package convex_hull.ch_ga.domain;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import convex_hull.utilities.ConvexHullUtilities;
import ga.DNA;
import ga.domain.Chromosome;
import ga.domain.GeneticAlgorithm;
import ga.domain.Population;
import ga.techniques.CrossOverTechnique;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;
import ga.techniques.SelectionTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CH_GeneticAlgorithm extends GeneticAlgorithm {

    private List<Point> points;
    //    private Canvas canvas = Canvas.getInstance();
    private double startTime;

    public CH_GeneticAlgorithm(List<Point> points, int populationCount, double mutationRate, FitnessTechnique fitnessTechnique, SelectionTechnique selectionTechnique, CrossOverTechnique crossOverTechnique, Map<Integer, MutationTechnique> mutationTechniqueMap) {
        super(populationCount, mutationRate, fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        this.points = points;
        startTime = System.nanoTime();
    }

    public double getStartTime() {
        return startTime;
    }

    public void initialGeneration() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            List<Point> randomConvexHull = ConvexHullUtilities.getRandomPoints(points, 3);
            DNA DNA = new CH_DNA(points, randomConvexHull, getFitnessTechnique());
            Chromosome chromosome = new Chromosome(DNA);
            chromosomes.add(chromosome);
        }
        Population initialPopulation = new Population(chromosomes);
        this.setPopulation(initialPopulation);
    }

    public void eliteGeneration() {
        DNA bestDNA = getFittestChromosomeEver().getDNA();
        Map<Integer,List<Point>> geneMap = bestDNA.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> poorConvexHullPoints = new ArrayList<>(geneMap.get(1));
        List<Point> outsidePoints = geneMap.get(2);
        List<Point> sickJoints = geneMap.get(3);
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            DNA DNA = new CH_DNA(points, poorConvexHullPoints, getFitnessTechnique());
            Chromosome chromosome = new Chromosome(DNA);
            chromosomes.add(chromosome);
        }
        Population elitePopulation = new Population(chromosomes);
        this.setPopulation(elitePopulation);

    }

    @Override
    public void draw() {
//        canvas.initializeComponents();
//        JStatsPanel jStatsPanel = new JStatsPanel(this);
//        canvas.getContentPane().add(jStatsPanel, BorderLayout.NORTH);
//        canvas.getContentPane().add(this, BorderLayout.CENTER);
//        canvas.validate();
//        canvas.repaint();
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
                .append("Convex points: " + getFittestChromosomeEver().getDNA().getGene().get(1).size()).append("\n") ;return geneticAlgorithmBuilder.toString();
    }
}