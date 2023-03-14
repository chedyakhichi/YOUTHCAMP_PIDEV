package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Activity;
import tn.esprit.integration1.Entities.ActivityType;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    @Query("select a.PriceA FROM Activity a where a.center.idcenter = :id ")
    Float ActivityPrice (@Param("id") Integer idCenter);
    @Query("select a FROM Activity a where  a.activityType = :at")
    List<Activity> FiltreActivity(@Param("at") ActivityType activityType);



}
