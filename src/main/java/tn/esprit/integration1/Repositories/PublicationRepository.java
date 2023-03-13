package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.TypePublication;

import java.util.List;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, Integer> {
    @Query(value = "SELECT p FROM Publication p  WHERE p.typePublication= :typePub")
    List<Publication> PublicationByType(@Param("typePub") TypePublication typePub);
}