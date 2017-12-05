package convex_hull.ch_ga.domain;

import convex_hull.ch_ga.CH_DNA;
import convex_hull.domain.Point;
import ga.domain.Chromosome;
import ga.domain.DNA;
import ga.domain.GeneticAlgorithm;
import ga.domain.Population;
import ga.techniques.FitnessTechnique;
import ga.techniques.MutationTechnique;
import ga.techniques.SelectionTechnique;
import view.Canvas;
import view.JPolygonComponent;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CH_GeneticAlgorithm extends GeneticAlgorithm {

    private Canvas canvas;
    private List<Point> points;

    public CH_GeneticAlgorithm(List<Point> points, Canvas canvas, int populationCount, double mutationRate, FitnessTechnique fitnessTechnique, SelectionTechnique selectionTechnique, Map<Integer, MutationTechnique> mutationTechniqueMap) {
        super(populationCount, mutationRate, fitnessTechnique, selectionTechnique, mutationTechniqueMap);
        this.canvas = canvas;
        this.points = points;
    }

    public void initialGeneration() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            Set<Point> randomPoints = new LinkedHashSet<>();
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
            DNA DNA = new CH_DNA(randomPoints, points, this);
            Chromosome chromosome = new Chromosome(DNA);
            chromosomes.add(chromosome);
        }
        Population initialPopulation = new Population(chromosomes, this);
        this.setPopulation(initialPopulation);
    }

    public void eliteGeneration() {
        DNA bestDNA = this.getFittestChromosomeEver().getDNA();
        bestDNA.setGeneticAlgorithm(this);
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            DNA DNA = new CH_DNA(new LinkedHashSet<>(bestDNA.getGene()), bestDNA.getGene(), this);
            Chromosome chromosome = new Chromosome(DNA);
            chromosomes.add(chromosome);
        }
        Population elitePopulation = new Population(chromosomes, this);
        this.setPopulation(elitePopulation);

//        List<Chromosome> chromosomes = new ArrayList<>();
//        Chromosome fittestEver = this.getFittestChromosomeEver();
//        for (int i = 0; i < this.getPopulationCount(); i++) {
//            DNA DNA = new CHDNA(new LinkedHashSet<>(fittestEver.getDNA().getGene()), new ArrayList<>(fittestEver.getDNA().getGene()), this.getMutationTechniqueMap(), this.getFitnessTechnique());
//            Chromosome chromosome = new CHChromosome(DNA);
//            chromosomes.add(chromosome);
//        }
//        this.setPopulation(new CHPopulation(chromosomes));
    }

    @Override
    public void draw() {
        List<Point> convexHull = this.getFittestChromosomeEver().getDNA().getGene();
        JPolygonComponent jPolygon = new JPolygonComponent(convexHull, points);
        jPolygon.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
        canvas.getContentPane().removeAll();
        canvas.getContentPane().add(jPolygon, BorderLayout.CENTER);
        canvas.revalidate();
        canvas.repaint();
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
            if (bestPopulationChromosome.getDNA().getOutsidePoints().size() == bestChromosomeEver.getDNA().getOutsidePoints().size()) {
                if (bestPopulationChromosome.getDNA().getGene().size() < bestChromosomeEver.getDNA().getGene().size()) {
                    this.setBestFitnessEver(bestPopulationFitness);
                    this.setFittestChromosomeEver(bestPopulationChromosome);
                }
            }
        }
    }

}

