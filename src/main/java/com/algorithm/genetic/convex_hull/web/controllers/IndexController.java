package com.algorithm.genetic.convex_hull.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String showIndex() {
        return "index";
    }

}
