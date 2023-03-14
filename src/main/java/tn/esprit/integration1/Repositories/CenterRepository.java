package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center,Integer> {
    @Query(value = "SELECT  sum(R.Price)  FROM Reservation R Where R.center.idcenter = :id ")
    public Integer totalMoneyCenter(@Param("id") Integer idCenter );
    @Query(value = "SELECT sum(R.nbReservationCenter)  FROM Reservation R Where R.center.idcenter = :id ")
    public Integer totalReservationCenter(@Param("id") Integer idCenter );

}
