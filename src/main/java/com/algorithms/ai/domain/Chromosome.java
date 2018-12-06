package com.algorithms.ai.domain;

/**
 * The type Chromosome.
 *
 * @param <T> the type parameter
 */
public class Chromosome<T> {

    private T gene;
    private double fitness;

    /**
     * Instantiates a new Chromosome.
     *
     * @param gene the gene
     */
    public Chromosome(T gene) {
        this.gene = gene;
    }


    /**
     * Gets gene.
     *
     * @return the gene
     */
    public T getGene() {
        return gene;
    }

    /**
     * Sets gene.
     *
     * @param gene the gene
     */
    public void setGene(T gene) {
        this.gene = gene;
    }

    /**
     * Gets fitness.
     *
     * @return the fitness
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * Sets fitness.
     *
     * @param fitness the fitness
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
