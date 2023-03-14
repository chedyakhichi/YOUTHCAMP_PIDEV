package tn.esprit.integration1.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Role;
import tn.esprit.integration1.Entities.User;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByLoyaltyPointsExpireDateBefore(LocalDate expirationDate);
    @Query(" Select u from User u where (u.adress_livraison= :adress) and (u.role= :role ) ")
    public User  finduser (@Param("adress") String adress, @Param("role") Role role);
///othman



}