package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.PointDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends CrudRepository<PointDAO,Long> {

}
