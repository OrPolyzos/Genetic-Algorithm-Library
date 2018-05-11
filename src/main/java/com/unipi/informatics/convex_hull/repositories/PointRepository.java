package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.CH_PointDao;
import com.unipi.informatics.convex_hull.dao.PointDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends CrudRepository<PointDao, Long> {

}
