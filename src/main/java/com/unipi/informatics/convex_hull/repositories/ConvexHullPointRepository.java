package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.CH_PointDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvexHullPointRepository extends CrudRepository<CH_PointDao, Long> {

}
