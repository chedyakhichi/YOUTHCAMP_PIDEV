package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Delivery;
import tn.esprit.integration1.Entities.DeliveryStatus;
import tn.esprit.integration1.Entities.DeliveryType;


import java.time.LocalDate;
import java.util.List;


@Repository
public interface DeliveryRepository extends JpaRepository <Delivery,Integer>{
  @Query("select d from Delivery d where (d.adress= :adress) and (d.etat= :etat)")
    public List<Delivery> retrieveDeliveries(@Param("adress") String adress,@Param("etat") Integer etat);

List<Delivery> findAllByDeliveryStatusIsLike(DeliveryStatus d);

  @Query(value = "SELECT * FROM Delivery ORDER BY RAND() LIMIT 1", nativeQuery = true)
  Delivery findRandomLivraison();

 // @Query("SELECT count(d) FROM Delivery d where( (d.user.iduser= :id) and (d.deliveryType= :type)and (d.deliveryDate= :date))")
 // public Integer getnbdeliveries(@Param("deliveryDate") LocalDate date, @Param("deliveryType") DeliveryType deliveryType , @Param("user.user") Integer id);


  @Query("SELECT sum(d.poids) FROM Delivery d where (( (d.user.iduser= :idUser)and(d.deliveryDate= :deliveryDate))and((d.deliveryType= :deliveryType)and (d.adress= :adress) ))")
  public Integer getpoidsdeliveries(@Param("idUser") Integer id, @Param("deliveryDate")LocalDate deliveryDate, @Param("deliveryType") DeliveryType deliveryType, @Param("adress")String adress);

  List<Delivery> findAllByAdressIsLike(String adress);
  List<Delivery> findAllByUser_IduserAndDeliveryDate(Integer id, LocalDate date );



  @Query("SELECT d FROM Delivery d where ( (d.user.iduser= :idUser)and(d.deliveryDate= :deliveryDate))")
  public List<Delivery> chercherdeliveries(@Param("idUser") Integer id,@Param("deliveryDate")LocalDate deliveryDateryType);







//  List<Delivery> retrieveDeliveries(String address,Integer status);


  // List<Delivery> findAllByAdressIsLikeAndEtatIs(String adress,Integer etat);

    //  Set<Etudiant> findByNomELikeAndPrenomELike(String NomE, String PrenomE);
    //List <Etudiant>findAllByDepartementIdDepartement(Integer idDepartement);

}
