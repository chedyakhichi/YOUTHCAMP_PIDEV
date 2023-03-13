package tn.esprit.integration1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.MotInterdit;

@Repository
public interface MotInterditRepository extends CrudRepository<MotInterdit, Integer> {
}
