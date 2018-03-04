package com.unipi.informatics.convex_hull.web.controllers;

import com.unipi.informatics.convex_hull.Convex_Hull_Problem;
import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDAO;
import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class IndexController {

    @Autowired
    GeneticAlgorithmRepository geneticAlgorithmRepository;

    @RequestMapping(value = "/")
    public GeneticAlgorithmDAO showIndex() {
        GeneticAlgorithm geneticAlgorithm = new Convex_Hull_Problem(500, 500, 0.03, 500, 150).solve();
        GeneticAlgorithmDAO geneticAlgorithmDAO = geneticAlgorithmRepository.save(GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm));
        return geneticAlgorithmDAO;
    }

}
