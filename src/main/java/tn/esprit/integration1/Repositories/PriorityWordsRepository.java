package tn.esprit.integration1.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.integration1.Entities.PriorityWords;

import java.util.List;

public interface PriorityWordsRepository extends JpaRepository<PriorityWords, Integer> {
    @Query("SELECT b.word FROM PriorityWords b")
    public List<String> getpriwords();

}

