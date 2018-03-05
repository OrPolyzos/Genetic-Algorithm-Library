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

public class CH_GeneticAlgorithm extends GeneticAlgorithm<Map<Integer, List<Point>>> {

    private List<Point> points;

    public CH_GeneticAlgorithm(List<Point> points, int populationCount, double mutationRate, FitnessTechnique<Map<Integer,List<Point>>> fitnessTechnique, SelectionTechnique<Map<Integer, List<Point>>> selectionTechnique, CrossOverTechnique<Map<Integer,List<Point>>> crossOverTechnique, Map<Integer, MutationTechnique<Map<Integer,List<Point>>>> mutationTechniqueMap) {
        super(populationCount, mutationRate, fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void initialGeneration() {
        List<Chromosome<Map<Integer, List<Point>>>> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            List<Point> randomConvexHull = ConvexHullUtilities.getRandomPoints(points, 3);
            DNA<Map<Integer, List<Point>>> DNA = new CH_DNA(points, randomConvexHull);
            Chromosome<Map<Integer, List<Point>>> chromosome = new CH_Chromosome<>(DNA, getFitnessTechnique());
            chromosomes.add(chromosome);
        }
        Population<Map<Integer, List<Point>>> initialPopulation = new CH_Population<>(chromosomes);
        this.setPopulation(initialPopulation);
    }

    public void eliteGeneration() {
        DNA<Map<Integer, List<Point>>> bestDNA = getFittestChromosomeEver().getDNA();
        Map<Integer, List<Point>> geneMap = bestDNA.getGene();
        List<Point> points = geneMap.get(0);
        List<Point> poorConvexHullPoints = new ArrayList<>(geneMap.get(1));
        List<Chromosome<Map<Integer, List<Point>>>> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            DNA<Map<Integer, List<Point>>> DNA = new CH_DNA(points, poorConvexHullPoints);
            Chromosome<Map<Integer, List<Point>>> chromosome = new CH_Chromosome<>(DNA, getFitnessTechnique());
            chromosomes.add(chromosome);
        }
        Population<Map<Integer, List<Point>>> elitePopulation = new CH_Population<>(chromosomes);
        this.setPopulation(elitePopulation);

    }

    @Override
    public void draw() {
    }

    @Override
    public void findFittestChromosomeEver() {
        Chromosome<Map<Integer, List<Point>>> bestChromosomeEver = this.getFittestChromosomeEver();
        double bestFitnessEver = this.getBestFitnessEver();
        Chromosome<Map<Integer, List<Point>>> bestPopulationChromosome = this.getPopulation().getFittestChromosome();
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

    @Override
    public String toString() {
        return "Generation: " + getGenerationsCounter() + "\n" + "Outside Points: " + getFittestChromosomeEver().getDNA().getGene().get(2).size() + "\n" + "Sick Joints: " + getFittestChromosomeEver().getDNA().getGene().get(3).size() + "\n" + "Intersections: " + getFittestChromosomeEver().getDNA().getIntersections() + "\n" + "Convex points: " + getFittestChromosomeEver().getDNA().getGene().get(1).size() + "\n";
    }
}