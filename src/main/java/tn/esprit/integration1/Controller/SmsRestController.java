package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.integration1.Entities.Delivery;
import tn.esprit.integration1.Services.DeliveryService;
import tn.esprit.integration1.Services.SMSService;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/SMS")
@Slf4j
public class SmsRestController {
    @Autowired
    SMSService smsService;
    @Autowired
    DeliveryService deliveryService ;




    //@Scheduled(cron="*/60 * * * * *")
  // @Scheduled(cron="0 0 9 * * *")
    @PostMapping("/ProcessSms")
    public String gProcessSms() {
   // public String gProcessSms(@RequestBody SMSSnedRequest sendRequest) {
    // log.info("processSMS started  sendRequest :"+sendRequest.toString());

     List<Delivery> deliveryList = deliveryService.retrieveAllLivraisonslivrés();
        for (Delivery delivery : deliveryList) {
            String a = String.valueOf(delivery.getUser().getPhone());
             String mesaage  ="Merci pour votre confiance de nous choisir .aller au site https://fr-fr.facebook.com/ pour consulter votre points fidelités";

        smsService.senSMS(a,mesaage);
    }
        return"dbfgxd";
    }








}
