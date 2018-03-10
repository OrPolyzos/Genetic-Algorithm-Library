package com.unipi.informatics.convex_hull.services;

import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GeneticAlgorithmService<T> {

    @Autowired
    private GeneticAlgorithmRepository geneticAlgorithmRepository;

    public GeneticAlgorithmDao save(GeneticAlgorithm<Map<Integer,List<Point>>> geneticAlgorithm){
        return geneticAlgorithmRepository.save(GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm));
    }
}
