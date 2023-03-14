package tn.esprit.integration1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.integration1.Entities.Charity;

public interface CharityRepository extends JpaRepository<Charity, Integer> {
//    List<Charity> findByOrderByTotalDonationsAsc();

}