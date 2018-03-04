package com.unipi.informatics.convex_hull.ch_ga.services;

import com.unipi.informatics.convex_hull.converters.PointConverter;
import com.unipi.informatics.convex_hull.dao.ChromosomeDAO;
import com.unipi.informatics.convex_hull.dao.PointDAO;
import com.unipi.informatics.convex_hull.domain.Point;
import com.unipi.informatics.convex_hull.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

//    PointDAO save(Point point, ChromosomeDAO chromosomeDAO) {
//        return pointRepository.save(PointConverter.convertToPointDAO(point, chromosomeDAO));
//    }
}
