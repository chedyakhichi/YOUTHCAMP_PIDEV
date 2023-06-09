package tn.esprit.integration1.Services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.*;
import tn.esprit.integration1.Interfaces.ICommandService;
import tn.esprit.integration1.Repositories.CommandRepository;
import tn.esprit.integration1.Repositories.DeliveryRepository;
import tn.esprit.integration1.Repositories.UserRepository;
import tn.esprit.integration1.dto.CommandDto;
import tn.esprit.integration1.validator.CommandValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommandServiceImpl implements ICommandService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommandRepository commandRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public Command createCommand(CommandDto commandDto) {
        List<String> errors = CommandValidator.validate(commandDto);

        String ref=this.generateRandomString(10);

        if (!errors.isEmpty()) {
            log.error("Command is not valid: {}", errors);
            return null;
        }
        Command command = CommandDto.toEntity(commandDto);
        command.setRef(ref);
        command.setCommmandType(CmdType.PENDING); // set status to PENDING
        commandRepository.save(command);

        return command;
    }

    @Override
    public String cancelCommand(Integer commandId) {
        Command command = commandRepository.findById(commandId).orElse(null);

        if (command == null) {
            return "Command not found";
        }

        if (command.getCommmandType() == CmdType.CANCELLED) {
            return "Command has already been cancelled";
        }

        if (command.getCommmandType() == CmdType.CONFIRMED) {
            return "Command has already been finalized and cannot be cancelled";
        }

        if (command.getCommmandType() == CmdType.PENDING) {
            command.setCommmandType(CmdType.CANCELLED);
            commandRepository.save(command);
            return "Command cancelled";
        }

        return "Error cancelling command";
    }


    @Override
    public CommandDto updateCommand(CommandDto commandDto) {
        List<String> errors = CommandValidator.validate(commandDto);
        if (!errors.isEmpty()) {
            log.error("Command is not valid: {}", errors);
            return null;
        }

        Optional<Command> optionalCommand = commandRepository.findById(commandDto.getCommandeNumber());
        if (optionalCommand.isPresent()) {
            Command existingCommand = optionalCommand.get();
            existingCommand.setPaymentMethod(commandDto.getPaymentMethod());
            existingCommand.setCommmandType(commandDto.getCommandType());
            existingCommand.setWeight(commandDto.getWeight());
            existingCommand.setDeliveryDate(commandDto.getDeliveryDate());
            Command updatedCommand = commandRepository.save(existingCommand);
            return CommandDto.fromEntity(updatedCommand);
        } else {
            log.error("Command with ID {} not found", commandDto.getCommandeNumber());
            return null;
        }
    }

    @Override
    public void deleteCommand(Integer commandeNumber) {
        if (commandeNumber == null) {
            log.error("Invalid command ID");
            return;
        }

        commandRepository.deleteById(commandeNumber);
    }

    @Override
    public CommandDto getCommandById(Integer commandeNumber) {
        Optional<Command> optionalCommand = commandRepository.findById(commandeNumber);
        if (optionalCommand.isPresent()) {
            Command command = optionalCommand.get();
            return CommandDto.fromEntity(command);
        } else {
            log.error("Command with id {} not found", commandeNumber);
            return null;
        }
    }

    @Override
    public List<CommandDto> getAllCommands() {
        List<Command> commands = commandRepository.findAll();
        List<CommandDto> commandDtos = new ArrayList<>();
        for (Command command : commands) {
            commandDtos.add(CommandDto.fromEntity(command));
        }
        return commandDtos;
    }



    @Override
    public void assignCommandToUser(Integer commandId, Integer userId) {
        Command command = commandRepository.findById(commandId).orElseThrow(() -> new RuntimeException("Command not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        command.setUser(user);
        commandRepository.save(command);
    }

    @Override
    public void assignDeliveryToCommand(Integer deliveryId, Integer commandId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);
        Command command = commandRepository.findById(commandId).orElse(null);

        assert command != null;
        command.setDelivery(delivery);
        commandRepository.save(command);
    }

    /**calculateTotalCost before the redeem */
    @Override
    public String calculateTotalCostPerCommand(Integer commandId) {
        Optional<Command> optionalCommand = commandRepository.findById(commandId);

        if (!optionalCommand.isPresent()) {
            return "Command not found";
        }

        Command command = optionalCommand.get();

        BigDecimal total = calculateCommandTotal(command);

        if (total.equals(BigDecimal.ZERO)) {
            return "Error calculating total cost";
        }

        return "Total cost of command " + commandId + ": " + total;
    }

    private BigDecimal calculateCommandTotal(Command command) {
        BigDecimal total = BigDecimal.ZERO;

        for (LineCmd lineCmd : command.getCommandLines()) {
            Product product = lineCmd.getProduct();
            Integer requestedQuantity = lineCmd.getQuantite();
            BigDecimal lineTotal;

            if (product.getIsRental()) {
                Integer nbrRentalPerDays = lineCmd.getNbrRentalPerDays();
                BigDecimal rentalPricePerDay = product.getPrice();
                lineTotal = rentalPricePerDay.multiply(BigDecimal.valueOf(nbrRentalPerDays)).multiply(BigDecimal.valueOf(requestedQuantity));
            } else {
                lineTotal = product.getPrice().multiply(BigDecimal.valueOf(requestedQuantity));
            }

            total = total.add(lineTotal);
            lineCmd.setTotal(lineTotal);

            log.info("Total cost for this product line: " + lineTotal);
        }

        command.setTotalC(total);

        try {
            commandRepository.save(command);
        } catch (Exception e) {
            log.error("Error saving command " + command.getCommandeNumber() + " to the database", e);
            return BigDecimal.ZERO;
        }

        return total;
    }






    private final BigDecimal POINTS_TO_DINARS_CONVERSION_RATE = BigDecimal.valueOf(0.1);

    @Override
    public String redeemPointsForDiscount(Integer commandId, Integer pointsToRedeem) {
        Command command = commandRepository.findById(commandId).orElse(null);
        if (command == null) {
            return "Commande avec identifiant " + commandId + " introuvable";
        }

        User user = command.getUser();
        BigDecimal purchaseAmount = command.getTotalC();

        BigDecimal pointsWorth = BigDecimal.valueOf(pointsToRedeem).multiply(POINTS_TO_DINARS_CONVERSION_RATE);
        BigDecimal availablePointsWorth = BigDecimal.valueOf(user.getLoyaltyPts()).multiply(POINTS_TO_DINARS_CONVERSION_RATE);
        if (pointsWorth.compareTo(availablePointsWorth) > 0) {
            return "Pas assez de points de fidélité pour le rachat";
        }
        // check if points have expired
        if (user.getLoyaltyPointsExpireDate() != null && user.getLoyaltyPointsExpireDate().isBefore(LocalDate.now())) {
            return "Les points de fidélité ont expiré";
        }
        double discountPercentage = (double) pointsToRedeem * POINTS_TO_DINARS_CONVERSION_RATE.doubleValue() / command.getTotalC().doubleValue() * 100;
        BigDecimal discountAmount = command.getTotalC().multiply(BigDecimal.valueOf(discountPercentage / 100)).setScale(2, RoundingMode.HALF_UP);

        user.setLoyaltyPts(user.getLoyaltyPts() - pointsToRedeem);
        command.setDiscountAmount(discountAmount);
        command.setTotalC(purchaseAmount.subtract(discountAmount));
        userRepository.save(user);
        commandRepository.save(command);

        return "Points de fidélité rachetés avec succès pour une remise de "
                + discountPercentage + "% sur la commande d'identifiant " + commandId
                + ". Nouveau total de la commande : " + command.getTotalC();
    }

    public String generateRandomString(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }


}




