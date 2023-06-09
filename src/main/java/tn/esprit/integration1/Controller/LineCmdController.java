package tn.esprit.integration1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Interfaces.ILineCmdService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/line-cmds")
public class LineCmdController {

    @Autowired
    private ILineCmdService lineCmdService;

    @PostMapping("/createLineCmdAndAssignProduct/{productId}/{quantite}/{nbrRentalPerDays}")
    public String createLineCmdAndAssignProduct( @PathVariable Integer productId,
                                                  @PathVariable Integer quantite,
                                                  @RequestParam(required = false, defaultValue = "1") Integer nbrRentalPerDays) {
        return lineCmdService.createLineCmdAndAssignProduct(productId, quantite, nbrRentalPerDays);
    }

    @PutMapping("/{lineCmdId}/assignCommand/{commandId}")
    public void assignCommandToLineCmd(@PathVariable Integer lineCmdId, @PathVariable Integer commandId) {
        lineCmdService.assignCommandToLineCmd(lineCmdId, commandId);
    }

    @PostMapping("/getTotalForLineCmdAndSaveIt/{lineCmdId}")
    public BigDecimal getTotalForLineCmd(@PathVariable("lineCmdId") Integer lineCmdId) {
        return lineCmdService.getTotalForLineCmd(lineCmdId);
    }


    @PutMapping("/updateQuantityAndTotal/{idLinecmd}/{productId}/{newQuantity}/{nbrRentalPerDays}")
        public String updateQuantityAndTotal(@PathVariable Integer idLinecmd,@PathVariable Integer productId,
                                       @PathVariable Integer newQuantity,
                                       @RequestParam(required = false, defaultValue = "1") Integer nbrRentalPerDays) {
        return lineCmdService.updateQuantityAndTotal(idLinecmd, productId, newQuantity, nbrRentalPerDays);
    }
}
