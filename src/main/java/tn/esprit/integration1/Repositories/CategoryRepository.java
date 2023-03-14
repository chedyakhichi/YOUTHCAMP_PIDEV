package tn.esprit.integration1.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.integration1.Entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
        List<Category> findByCategoryType(String categoryType);

        List<Category> findByNameContainingIgnoreCase(String categoryName);

//        List<Category> findByCategoryDtoCategoryTypeAndNameContainingIgnoreCase(String categoryType, String name);
    }


