package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.GeneticAlgorithmDao;
import com.unipi.informatics.ga.domain.GeneticAlgorithm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GeneticAlgorithmRepository extends CrudRepository<GeneticAlgorithmDao, Long> {

}
