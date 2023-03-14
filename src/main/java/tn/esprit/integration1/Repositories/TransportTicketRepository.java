package tn.esprit.integration1.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.integration1.Entities.TransportTicket;

public interface TransportTicketRepository extends JpaRepository<TransportTicket, Integer> {



}
