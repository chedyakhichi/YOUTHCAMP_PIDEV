package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.TourReservation;


@Repository
public interface TourReservationRepository extends JpaRepository <TourReservation,Integer>{

   // public TourReservation findByTour_resIsLike(String tour);


    /*@Query("select t from TourReservation t where (t.tourRes= :adress)")
    public List<TourReservation> retrieveToursRes(@Param("adress") String adress);*/



  //  List<TourReservation> findAllByTourResIsLike(String adress);

}
