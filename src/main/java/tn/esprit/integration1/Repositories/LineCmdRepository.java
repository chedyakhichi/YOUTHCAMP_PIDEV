package tn.esprit.integration1.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.integration1.Entities.LineCmd;

public interface LineCmdRepository extends JpaRepository<LineCmd, Integer> {
}