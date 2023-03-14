package tn.esprit.integration1.Services;

import com.stripe.exception.CardException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.*;
import tn.esprit.integration1.Interfaces.ICommandFinalizerService;
import tn.esprit.integration1.Repositories.CommandRepository;
import tn.esprit.integration1.Repositories.LineCmdRepository;
import tn.esprit.integration1.Repositories.ProductRepository;
import tn.esprit.integration1.Repositories.UserRepository;
import tn.esprit.integration1.Services.stripe.ConfigService;
import tn.esprit.integration1.Services.stripe.PaymentService;
import tn.esprit.integration1.dto.CommandDto;
import tn.esprit.integration1.validator.CommandValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommandFinalizerServiceImpl implements ICommandFinalizerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LineCmdRepository lineCmdRepository;
    @Autowired
    private CommandRepository commandRepository;


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private PaymentService paymentService;



    private static final double TAX_RATE = 0.15;

    @Override
    public String finalizeCommand(Integer commandId) {
        Command command = commandRepository.findById(commandId).orElse(null);
        CommandDto commandDto = CommandDto.fromEntity(command);
        List<String> errors = CommandValidator.validate(commandDto);
        if (!errors.isEmpty()) {
            return String.join(" ", errors);
        }

        // Validate command status and payment method
        if (!CommandValidator.validateStatus(command.getCommmandType())) {
            return "Command has already been finalized or cancelled";
        }
        String paymentMethodError = CommandValidator.validatePaymentMethod(command.getPaymentMethod());
        if (paymentMethodError != null) {
            return paymentMethodError;
        }



        BigDecimal total = command.getTotalC();
        List<String> unavailableProducts = checkProductAvailability(command);

        if (!unavailableProducts.isEmpty()) {
            return buildUnavailableProductsErrorMessage(unavailableProducts);
        }
        // subtract product quantities and assign loyalty points
        subtractQuantity(command);
        assignPointsForPurchase(command, total);
        try {
            chargeCustomer(command, total);
        } catch (PaymentProcessingException e) {
            return e.getMessage();
        }
        finalizePendingCommand(command, total);
        // send confirmation email
        emailService.sendConfirmationEmail(command.getUser().getEmail(), command);
       // BigDecimal totalWithTax = addTax(total);
        return "Command finalized. Total cost: " + total;
    }

    private List<String> checkProductAvailability(Command command) {
        List<String> unavailableProducts = new ArrayList<>();

        for (LineCmd lineCmd : command.getCommandLines()) {
            Product product = lineCmd.getProduct();
            Integer requestedQuantity = lineCmd.getQuantite();

            if (!isProductAvailable(product, requestedQuantity)) {
                unavailableProducts.add(product.getName() + " (" + requestedQuantity + " requested, " + product.getQuantityAvailable() + " available)");
            }
        }

        return unavailableProducts;
    }

    private boolean isProductAvailable(Product product, Integer requestedQuantity) {
        Integer availableQuantity = product.getQuantityAvailable();
        log.info("Available quantity in stock: " + availableQuantity);
        log.info("Quantity requested by client: " + requestedQuantity);
        return requestedQuantity <= availableQuantity;
    }

    private void subtractQuantity(Command command) {
        for (LineCmd lineCmd : command.getCommandLines()) {
            Product product = lineCmd.getProduct();
            Integer requestedQuantity = lineCmd.getQuantite();

            Integer availableQuantity = product.getQuantityAvailable();
            if (availableQuantity >= requestedQuantity) {
                product.setQuantityAvailable(availableQuantity - requestedQuantity);
                product.incrementSales(requestedQuantity); // Increment the sales counter for the product
                try {
                    productRepository.save(product);
                    lineCmdRepository.save(lineCmd);
                } catch (Exception e) {
                    log.error("Error saving product " + product.getName() + " or line command " + lineCmd.getId() + " to the database", e);
                }
            } else {
                log.error("Insufficient quantity available for product " + product.getName());
            }
        }
    }


    private void assignPointsForPurchase(Command command, BigDecimal total) {
        Integer pointsEarned = total.intValue(); // assign 1 point for every $1 spent
        User user = command.getUser();
        if (command.getDonation() && isSustainable(command)) { // verify if isDonated flag is true and if any product belongs to the sustainable category
            pointsEarned *= 6; // triple and double loyalty points
        } else if (command.getDonation()) { // verify if isDonated flag is true
            pointsEarned *= 3; // triple loyalty points
        } else if (isSustainable(command)) { // check if any product belongs to the sustainable category
            pointsEarned *=2 ;
        }
        user.setLoyaltyPts(user.getLoyaltyPts()+ pointsEarned);
        userRepository.save(user);
    }


    private void finalizePendingCommand(Command command, BigDecimal total) {
        command.setCommmandType(CmdType.CONFIRMED);
        command.setTotalC(total);
        commandRepository.save(command);
    }

    private String buildUnavailableProductsErrorMessage(List<String> unavailableProducts) {
        StringBuilder sb = new StringBuilder("The following products are not available:");

        for (String product : unavailableProducts) {
            sb.append("\n- ").append(product);
        }

        return sb.toString();
    }
    private boolean isSustainable(Command command) {
        return command.getCommandLines().stream()
                .anyMatch(lineCmd -> lineCmd.getProduct().getCategory().getName().contains("*"));
    }
    private void chargeCustomer(Command command, BigDecimal total) throws PaymentProcessingException {
        String cardToken = configService.getCardToken(); // read from configuration
        String currency = configService.getCurrencyCode(); // read from configuration
        try {
            paymentService.charge(cardToken, total.multiply(new BigDecimal(100)).intValue(), currency);
        } catch (CardException e) {
            throw new PaymentProcessingException("Error processing payment: " + e.getMessage());
        } catch (Exception e) {
            throw new PaymentProcessingException("Unknown error during payment processing: " + e.getMessage());
        }
    }


    public class PaymentProcessingException extends Exception {
        public PaymentProcessingException(String message) {
            super(message);
        }
    }
    public String generateRandomString(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }

    private BigDecimal addTax(BigDecimal total) {
        BigDecimal taxAmount = total.multiply(new BigDecimal(TAX_RATE));
        return total.add(taxAmount);
    }
}




