package com.unipi.informatics.convex_hull.ch_ga.services;

import com.unipi.informatics.convex_hull.repositories.ChromosomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChromosomeService {

    @Autowired
    private ChromosomeRepository chromosomeRepository;

}
