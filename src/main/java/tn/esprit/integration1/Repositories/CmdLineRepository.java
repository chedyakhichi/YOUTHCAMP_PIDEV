package tn.esprit.integration1.Repositories;

import tn.esprit.integration1.Entities.LineCmd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CmdLineRepository extends JpaRepository<LineCmd, Integer> {
}