package com.unipi.informatics.convex_hull.services;

import com.unipi.informatics.convex_hull.ch_ga.domain.CH_Gene;
import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneticAlgorithmService<T> {

    @Autowired
    private GeneticAlgorithmRepository geneticAlgorithmRepository;

    public GeneticAlgorithmDao save(GeneticAlgorithm<CH_Gene> geneticAlgorithm) {
        return geneticAlgorithmRepository.save(GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm));
    }
}
