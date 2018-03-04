package com.unipi.informatics.convex_hull.services;

import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDAO;
import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneticAlgorithmService {

    @Autowired
    GeneticAlgorithmRepository geneticAlgorithmRepository;

    public GeneticAlgorithmDAO save(GeneticAlgorithm geneticAlgorithm){
        return geneticAlgorithmRepository.save(GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm));
    }
}
