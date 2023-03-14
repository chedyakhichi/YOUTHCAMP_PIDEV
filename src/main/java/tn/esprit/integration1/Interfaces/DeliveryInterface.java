package tn.esprit.integration1.Interfaces;

import tn.esprit.integration1.Entities.Delivery;
import tn.esprit.integration1.Entities.DeliveryType;


import java.time.LocalDate;
import java.util.List;

public interface DeliveryInterface {
    List<Delivery> retrieveAllDeliverys();

    Delivery updateDelivery(Delivery ce);

    Delivery addDelivery(Delivery ce);

    Delivery retrieveDelivery(Integer idDelivery);

    void removeTDelivery(Integer idDelivery);

    void removeDelivery(Integer idDelivery);
    public void affectDeliveryToUser2(String adress);

    List<Delivery> Chercherdeliveriespourcejour(Integer id, LocalDate date);

    public Integer poidsdeliveries2(Integer id, LocalDate date, DeliveryType type, String adress);

    Delivery randomDelivery();

    public void affectDeliveryToUser(Integer DeliveryId, Integer userId);

    List<Delivery> retrieveAllLivraisonslivrés();
    // public void assignCommandToDelivery(Command command, Delivery delivery) ;

   }
