package ga;


import java.util.Map;

public abstract class GeneticAlgorithm {

    public double bestFitnessEver;
    private int populationCount;
    private FitnessTechnique fitnessTechnique;
    private double mutationRate;
    private Map<Integer, MutationTechnique> mutationTechniqueMap;
    private Chromosome fittestChromosomeEver;

    private Population population;

    private int generationsCounter;

    public GeneticAlgorithm(int populationCount, FitnessTechnique fitnessTechnique, double mutationRate, Map<Integer, MutationTechnique> mutationTechniqueMap) {
        this.mutationRate = mutationRate;
        this.populationCount = populationCount;
        this.fitnessTechnique = fitnessTechnique;
        this.mutationTechniqueMap = mutationTechniqueMap;
    }

    public void run() {
        bestFitnessEver = Double.MIN_VALUE;
        fittestChromosomeEver = population.getFittestChromosome();
        while (bestFitnessEver < 1) {
            findFittestChromosomeEver();
            nextGeneration();
            draw();
        }
//        System.out.println("Best Fitness of this Phase: " + bestFitnessEver);
    }

    private void findFittestChromosomeEver() {
        if (this.population.getFittestChromosome().getFitness() > this.bestFitnessEver) {
            this.bestFitnessEver = this.population.getFittestChromosome().getFitness();
            this.fittestChromosomeEver = this.population.getFittestChromosome().getCopy();
//            System.out.println("Generation: " + generationsCounter + " | Best Fitness (so far): " + bestFitnessEver);
        }
        else if (this.population.getFittestChromosome().getFitness() == this.bestFitnessEver) {
            if (this.population.getFittestChromosome().getDNA().getOutsidePoints().size() == this.fittestChromosomeEver.getDNA().getOutsidePoints().size()) {
                if (this.population.getFittestChromosome().getDNA().getGene().size() < this.fittestChromosomeEver.getDNA().getGene().size()) {
                    this.bestFitnessEver = this.population.getFittestChromosome().getFitness();
                    this.fittestChromosomeEver = this.population.getFittestChromosome().getCopy();
//                    System.out.println("Generation: " + generationsCounter + " | Best Fitness (so far): " + bestFitnessEver);
                }
            }
        }
    }

    public abstract void initialGeneration();

    public abstract void nextGeneration();

    public abstract Chromosome crossOver(Chromosome parentA, Chromosome parentB);

    public abstract void draw();

    public int getPopulationCount() {
        return populationCount;
    }

    public void setPopulationCount(int populationCount) {
        this.populationCount = populationCount;
    }

    public FitnessTechnique getFitnessTechnique() {
        return fitnessTechnique;
    }

    public void setFitnessTechnique(FitnessTechnique fitnessTechnique) {
        this.fitnessTechnique = fitnessTechnique;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public Map<Integer, MutationTechnique> getMutationTechniqueMap() {
        return mutationTechniqueMap;
    }

    public void setMutationTechniqueMap(Map<Integer, MutationTechnique> mutationTechniqueMap) {
        this.mutationTechniqueMap = mutationTechniqueMap;
    }

    public double getBestFitnessEver() {
        return bestFitnessEver;
    }

    public void setBestFitnessEver(double bestFitnessEver) {
        this.bestFitnessEver = bestFitnessEver;
    }

    public Chromosome getFittestChromosomeEver() {
        return fittestChromosomeEver;
    }

    public void setFittestChromosomeEver(Chromosome fittestChromosomeEver) {
        this.fittestChromosomeEver = fittestChromosomeEver;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public int getGenerationsCounter() {
        return generationsCounter;
    }

    public void setGenerationsCounter(int generationsCounter) {
        this.generationsCounter = generationsCounter;
    }
}
