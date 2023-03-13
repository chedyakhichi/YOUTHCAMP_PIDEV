package tn.esprit.integration1.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
}