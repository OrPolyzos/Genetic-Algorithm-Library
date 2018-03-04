package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.ChromosomeDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChromosomeRepository extends CrudRepository<ChromosomeDAO,Long>{
    ChromosomeDAO save(ChromosomeDAO chromosomeDAO);
}
