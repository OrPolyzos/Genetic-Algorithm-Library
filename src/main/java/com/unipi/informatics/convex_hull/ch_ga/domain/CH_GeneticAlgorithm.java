package com.unipi.informatics.convex_hull.ch_ga.domain;

import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.utilities.CH_Utilities;
import com.unipi.informatics.ga.domain.Chromosome;
import com.unipi.informatics.ga.domain.Dna;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import com.unipi.informatics.ga.domain.Population;
import com.unipi.informatics.ga.techniques.CrossOverTechnique;
import com.unipi.informatics.ga.techniques.FitnessTechnique;
import com.unipi.informatics.ga.techniques.MutationTechnique;
import com.unipi.informatics.ga.techniques.SelectionTechnique;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CH_GeneticAlgorithm extends GeneticAlgorithm<CH_Gene> {

    private List<Point> points;

    public CH_GeneticAlgorithm(List<Point> points, int populationCount, double mutationRate, FitnessTechnique<CH_Gene> ch_fitnessTechnique, SelectionTechnique<CH_Gene> selectionTechnique, CrossOverTechnique<CH_Gene> crossOverTechnique, Map<Integer, MutationTechnique<CH_Gene>> mutationTechniqueMap) {
        super(populationCount, mutationRate, ch_fitnessTechnique, selectionTechnique, crossOverTechnique, mutationTechniqueMap);
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void initialGeneration() {
        List<Chromosome<CH_Gene>> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            List<Point> randomConvexHull = CH_Utilities.getRandomPoints(points, 3);
            CH_Gene gene = new CH_Gene(points, randomConvexHull);
            Dna<CH_Gene> ch_dna = new CH_Dna(gene);
            Chromosome<CH_Gene> chromosome = new CH_Chromosome(ch_dna, getFitnessTechnique());
            chromosomes.add(chromosome);
        }
        Population<CH_Gene> initialPopulation = new CH_Population(chromosomes);
        this.setPopulation(initialPopulation);
    }

    public void eliteGeneration() {
        Dna<CH_Gene> bestDna = getFittestChromosomeEver().getDna();
        CH_Gene gene = bestDna.getGene();
        List<Chromosome<CH_Gene>> chromosomes = new ArrayList<>();
        for (int i = 0; i < this.getPopulationCount(); i++) {
            CH_Dna ch_dna = new CH_Dna(gene);
            CH_Chromosome chromosome = new CH_Chromosome(ch_dna, getFitnessTechnique());
            chromosomes.add(chromosome);
        }
        Population<CH_Gene> elitePopulation = new CH_Population(chromosomes);
        this.setPopulation(elitePopulation);
    }

    @Override
    public void findFittestChromosomeEver() {
        Chromosome<CH_Gene> bestChromosomeEver = this.getFittestChromosomeEver();
        double bestFitnessEver = this.getBestFitnessEver();
        Chromosome<CH_Gene> bestPopulationChromosome = this.getPopulation().getFittestChromosome();
        double bestPopulationFitness = bestPopulationChromosome.getFitness();
        if (bestPopulationFitness > bestFitnessEver) {
            this.setBestFitnessEver(bestPopulationFitness);
            this.setFittestChromosomeEver(bestPopulationChromosome);
        } else if (bestPopulationFitness == bestFitnessEver) {
            if (bestPopulationChromosome.getDna().getGene().getOutsidePoints().size() == bestChromosomeEver.getDna().getGene().getOutsidePoints().size()) {
                this.setBestFitnessEver(bestPopulationFitness);
                this.setFittestChromosomeEver(bestPopulationChromosome);
            }
        }

    }

    @Override
    public String toString() {
        return "Generation: " + getGenerationsCounter() + "\n" + "Outside Points: " + getFittestChromosomeEver().getDna().getGene().getOutsidePoints().size() + "\n" + "Sick Joints: " + getFittestChromosomeEver().getDna().getGene().getSickJoints().size() + "\n" + "Intersections: " + getFittestChromosomeEver().getDna().getGene().getIntersectionPoints().size() + "\n" + "Convex points: " + getFittestChromosomeEver().getDna().getGene().getConvexHull().size() + "\n";
    }
}