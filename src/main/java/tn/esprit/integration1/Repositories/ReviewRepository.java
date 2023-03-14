package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.ReviewC;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewC,Integer> {
    @Query("select sum(r.rating) FROM ReviewC r where r.center.idcenter = :id ")
    Integer RatingSum(@Param("id") Integer idCenter);
    @Query("select count(r.user) FROM ReviewC r where r.center.idcenter = :id ")
    Integer Countuser(@Param("id") Integer idCenter);
}
