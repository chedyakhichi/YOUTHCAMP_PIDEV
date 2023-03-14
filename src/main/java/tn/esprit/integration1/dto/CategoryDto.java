package tn.esprit.integration1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import tn.esprit.integration1.Entities.Category;
import tn.esprit.integration1.Entities.Product;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Integer categoryId;
    private String name;
    private String description;
    private String categoryType;
    private String code; // add this property

    @JsonIgnore
    private List<Product> products;

    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .categoryType(category.getCategoryType())
                .code(category.getCode())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setCategoryType(categoryDto.getCategoryType());
        category.setCode(categoryDto.getCode());
        return category;
    }
}
