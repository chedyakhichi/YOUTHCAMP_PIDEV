package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Delivery;
import tn.esprit.integration1.Entities.DeliveryType;
import tn.esprit.integration1.Services.DeliveryService;
import tn.esprit.integration1.Services.SMSService;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Delivery")

public class DeliveryRestController {

    DeliveryService deliveryService;
    SMSService smsService ;

  @GetMapping("/retrieve-all-Deliverys")
  public List<Delivery> getDeliverys() {
    List<Delivery> listDeliverys = deliveryService.retrieveAllDeliverys();
    return listDeliverys;
  }

  @GetMapping("/chercherlivraisonparjour/{user-id}")
  public List<Delivery> chercherlivraisonparjour(@PathVariable("user-id") Integer id) {
    LocalDate Date = LocalDate.from(LocalDateTime.now());
      List<Delivery> deliveries =deliveryService.Chercherdeliveriespourcejour(id,Date);
    return deliveries;
  }

  @PostMapping("/add-Delivery")
  public Delivery addDelivery (@RequestBody Delivery c) {
      Delivery delivery = deliveryService.addDelivery(c);
    return delivery;
  }

  @DeleteMapping("/remove-Delivery/{Delivery-id}")
  public void removeDelivery(@PathVariable("Delivery-id") Integer eId) {
    deliveryService.removeDelivery(eId);
  }


  @PutMapping("/update-Delivery")
  public Delivery updateDelivery(@RequestBody Delivery c) {
      Delivery delivery= deliveryService.updateDelivery(c);
    return delivery;
  }


    @PutMapping("/affectDeliveryToUser/{Delivery-id}/{userId}")
    public void affectDeliveryToUser(@PathVariable("Delivery-id")Integer deliveryid
    ,@PathVariable("userId")Integer userid)
    {
        deliveryService.affectDeliveryToUser(deliveryid,userid);
    }
  @PutMapping("/affectDeliveryToUser2/{adress}")
  public void affectDeliveryToUser2(@PathVariable("adress")String adress)
  {
    deliveryService.affectDeliveryToUser2(adress);
  }
  @GetMapping("/getnbdeliveries/{id}/{Dateliv}/{type}/{adress}")
  public Integer getnbdeliveries(@PathVariable("id") Integer id, @PathVariable(name ="Dateliv") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate Dateliv, @PathVariable("type") DeliveryType type, @PathVariable("adress") String adress){

    return deliveryService.poidsdeliveries2(id,Dateliv,type,adress);
  }

  @Scheduled(cron="0 0 12 * * *")
  @GetMapping("/randomDelivery")
  public Delivery randomDelivery() {
    System.out.println("vous avez la livraison gratuite");
    Delivery delivery = deliveryService.randomDelivery();
    String a = String.valueOf(delivery.getUser().getPhone());

    String mesaage  ="vous  avez gagnez une livraison gratuite ";

    smsService.senSMS(a,mesaage);

    return deliveryService.randomDelivery();








  }




    /*
@GetMapping("assignCommandToDelivery")
  public void assignCommandToDelivery(@RequestBody Command command,
                                      @RequestBody Delivery delivery) {
    deliveryService.assignCommandToDelivery(command,delivery);

  }*/


 /* @PutMapping("/affectContratToetudiant/{Tour-id}/{nomE}/{prenomE}")
  public Contrat affectContratToetudiant(@PathVariable("contrat-id") Integer contratId,
                                         @PathVariable("nomE") String nom,
                                         @PathVariable("prenomE") String prenom ) {

    return contratService.affectContratToEtudiant(contratId,nom,prenom);
  }*/
}
