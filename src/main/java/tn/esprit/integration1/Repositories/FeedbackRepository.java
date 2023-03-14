package tn.esprit.integration1.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Feedback;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    @Query("Select e From Feedback e order by e.nbrw desc")
    public List<Feedback> affichepriorty();
}
