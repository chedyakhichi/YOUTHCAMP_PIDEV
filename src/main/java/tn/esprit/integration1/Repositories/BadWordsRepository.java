package tn.esprit.integration1.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.BadWords;

import java.util.List;

@Repository
public interface BadWordsRepository extends JpaRepository<BadWords, Integer> {
    @Query("SELECT b.word FROM BadWords b")
    public List<String> getwords();

}
