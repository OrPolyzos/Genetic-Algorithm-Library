package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.ChromosomeConvexHullPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChromosomeConvexHullPointRepository extends CrudRepository<ChromosomeConvexHullPoint,Long> {

}
