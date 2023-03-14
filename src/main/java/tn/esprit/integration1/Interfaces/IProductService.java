package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    ProductDto createProductAndAssignToCategory(ProductDto productDto, Integer categoryId);
    void updateProduct(ProductDto updatedProductDto) ;
    void deleteProduct(Integer productId);
    ProductDto getProductById(Integer productId);
    List<ProductDto> getAllProducts();
    List<ProductDto> searchProductsByName(String name);

        //the Management of promotions/offers
     List<ProductDto> getProductsByPromotion(String promotionName);
     void addPromotionToProduct(Integer productId, String promotionName);
     void removePromotionFromProduct(Integer productId) ;
     void applyDiscountToProduct(Integer productId, BigDecimal discount) ;
    public void applyPercentageDiscountToProduct(Integer productId, float percentageDiscount) ;



    // Management of loyalty points
    void addLoyaltyPointsToUser(Integer userId, Float points);
    void subtractLoyaltyPointsFromUser(Integer userId, Float points);

    //Management of tool sales/rentals:
    List<ProductDto> getAllRentals();
    List<ProductDto> getAllSales();



}
