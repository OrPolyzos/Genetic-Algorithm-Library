package com.unipi.informatics.convex_hull.web.controllers;

import com.unipi.informatics.convex_hull.CH_Problem;
import com.unipi.informatics.convex_hull.converters.GeneticAlgorithmConverter;
import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RunController {

    @Autowired
    GeneticAlgorithmRepository geneticAlgorithmRepository;

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public GeneticAlgorithmDao run(@RequestParam int width, @RequestParam int height, @RequestParam double mutationRate, @RequestParam int populationCount, @RequestParam int pointsCount) {
        CH_Problem CH_problem = new CH_Problem(width,height,mutationRate,populationCount,pointsCount);//detailsForm.getMutationRate(), detailsForm.getPopulationCount(), detailsForm.getPointsCount());
        GeneticAlgorithm<Map<Integer,List<Point>>> geneticAlgorithm = CH_problem.solve();
        return GeneticAlgorithmConverter.convertToGeneticAlgorithmDAO(geneticAlgorithm);
    }

}
