package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Commentaire;

@Repository
public interface CommentaireRepository extends CrudRepository<Commentaire, Integer> {



    @Query(value = "SELECT count(c.Pubreact.idPub) FROM Commentaire c  WHERE (c.Pubreact.idPub=:idpub)")
    Integer comentaireparpublication(@Param("idpub") Integer idpub);





}