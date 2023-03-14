package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.Location;
import tn.esprit.integration1.Entities.LocationType;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    @Query("select c FROM Center c where c.location.locationType =:lt ")
    List<Center> CenterByLocatioType (@Param("lt") LocationType locationType);
}
