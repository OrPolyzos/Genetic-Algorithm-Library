package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneticAlgorithmRepository extends CrudRepository<GeneticAlgorithmDao, Long> {

}
