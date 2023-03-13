package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.Reaction;
import tn.esprit.integration1.Entities.TypeReaction;

import java.util.List;

@Repository
public interface ReactionRepository extends CrudRepository<Reaction, Integer> {

@Query(value = "SELECT count(r.idReac) FROM Reaction r  WHERE (r.Pubreact=:pub) and (r.typeReaction=:type)"
      )
    Integer MostReactionn(@Param("pub") Publication pub
                        ,@Param("type") TypeReaction type);



    @Query(value = "SELECT count(r.idReac) FROM Reaction r  WHERE (r.Pubreact=:pub)")
    Integer TopReaction(@Param("pub") Publication pub);


Reaction findByTypeReactionIsLike(TypeReaction t);
    @Query(value = "SELECT r FROM Reaction r  WHERE r.user.iduser= :iduser and (r.Pubreact.idPub= :idpub)"
    )
    List<Reaction> ReactionByUser(@Param("iduser") Integer iduser,@Param("idpub") Integer idupub);


}