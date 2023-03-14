package tn.esprit.integration1.Interfaces;

import java.math.BigDecimal;

public interface ILineCmdService {

     String createLineCmdAndAssignProduct(Integer productId, Integer quantite,Integer nbrRentalPerDays) ;

     void assignCommandToLineCmd(Integer lineCmdId, Integer commandId) ;
     public String updateQuantityAndTotal(Integer idLinecmd, Integer productId, Integer newQuantity, Integer nbrRentalPerDays) ;
     BigDecimal getTotalForLineCmd(Integer lineCmdId) ;

     }


