package com.unipi.informatics.convex_hull.repositories;

import com.unipi.informatics.convex_hull.dao.ChromosomeDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChromosomeRepository extends CrudRepository<ChromosomeDao, Long> {

}
