package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.Entities.Command;
import tn.esprit.integration1.dto.CommandDto;

import java.util.List;

public interface ICommandService {
     Command createCommand(CommandDto commandDto) ;
    String cancelCommand(Integer commandId) ;
    CommandDto updateCommand(CommandDto commandDto);
    void deleteCommand(Integer commandeNumber);
    CommandDto  getCommandById(Integer commandeNumber);
    List<CommandDto> getAllCommands();

    void assignCommandToUser(Integer commandId, Integer userId) ;
    void assignDeliveryToCommand(Integer deliveryId, Integer commandId) ;
    String calculateTotalCostPerCommand(Integer commandId) ;
    String redeemPointsForDiscount(Integer commandId, Integer pointsToRedeem) ;

    }
