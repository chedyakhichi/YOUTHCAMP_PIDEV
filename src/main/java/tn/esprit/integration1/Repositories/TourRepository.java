package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Tour;


@Repository
public interface TourRepository extends JpaRepository <Tour,Integer>{


    @Query("select t from Tour t where (t.locationName= :tour) ")
    public Tour retrievetour(@Param("tour") String tour);




}
