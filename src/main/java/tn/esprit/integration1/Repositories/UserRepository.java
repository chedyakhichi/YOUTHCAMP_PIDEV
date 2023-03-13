package tn.esprit.integration1.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.User;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByLoyaltyPointsExpireDateBefore(LocalDate expirationDate);
}