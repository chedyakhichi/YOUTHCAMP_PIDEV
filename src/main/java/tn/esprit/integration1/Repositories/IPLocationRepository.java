package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.integration1.Entities.IPlocation;


public interface IPLocationRepository extends JpaRepository<IPlocation,Long> {

    IPlocation findByIpAddress(String ip);
}
