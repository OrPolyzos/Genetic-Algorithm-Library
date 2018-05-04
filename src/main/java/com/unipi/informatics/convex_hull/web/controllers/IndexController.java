package com.unipi.informatics.convex_hull.web.controllers;

import com.unipi.informatics.convex_hull.repositories.GeneticAlgorithmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    GeneticAlgorithmRepository geneticAlgorithmRepository;

    @GetMapping(value = "/")
    public String showIndex() {
        return "index";
    }

}
