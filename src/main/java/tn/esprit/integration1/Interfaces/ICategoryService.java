package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.Entities.Category;
import tn.esprit.integration1.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
     CategoryDto createCategory(CategoryDto categoryDto) ;
     CategoryDto updateCategory(CategoryDto categoryDto) ;
     void deleteCategory(Integer categoryId) ;
     CategoryDto getCategoryById(Integer categoryId) ;
     List<CategoryDto> getAllCategories() ;
     List<CategoryDto> getCategoriesByType(String categoryType) ;
     List<CategoryDto> searchCategoriesByName(String categoryName) ;
     public void assignProductToCategory(Integer productId, Integer categoryId) ;
     //todo désaffecter
     public Category findCat(Integer id);

     }
