package tn.esprit.integration1.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.*;
import tn.esprit.integration1.Interfaces.DeliveryInterface;
import tn.esprit.integration1.Repositories.CommandRepository;
import tn.esprit.integration1.Repositories.DeliveryRepository;
import tn.esprit.integration1.Repositories.UserRepository;


import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class DeliveryService implements DeliveryInterface {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    UserRepository userRepository ;
    @Autowired
    CommandRepository commandRepository ;
    @Override
    public List<Delivery> retrieveAllDeliverys() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery updateDelivery(Delivery ce) {
        return deliveryRepository.save(ce);
    }

    @Override
    public Delivery addDelivery(Delivery ce) {
        return deliveryRepository.save(ce);
    }

    @Override
    public Delivery retrieveDelivery(Integer idDelivery) {
        return deliveryRepository.findById(idDelivery).orElse(null);
    }

    @Override
    public void removeTDelivery(Integer idDelivery) {

    }

    @Override
    public void removeDelivery(Integer idDelivery) {
          deliveryRepository.deleteById(idDelivery);
    }

    @Override
    public Delivery randomDelivery() {
        Delivery d = deliveryRepository.findRandomLivraison();
        d.setPrixLivraison(0);
        deliveryRepository.save(d);
        return d;
    }

   public void affectDeliveryToUser(Integer DeliveryId,Integer userId) {
       Delivery delivery = deliveryRepository.findById(DeliveryId).orElse(null);
       User e = userRepository.findById(userId).orElse(null);
       assert delivery != null;
       delivery.setUser(e);
       deliveryRepository.save(delivery);
       //   User e = userRepository.findUserByRoleIsLikeAndAdress_livraisonIsLike("livreur","aaa");

   }

    public void affectDeliveryToUser2(String address) {


        List<Delivery> deliveries = deliveryRepository.findAllByAdressIsLike(address);
        User user = userRepository.finduser(address, Role.DELIVERYMAN);

        for (Delivery delivery : deliveries) {
            Boolean etat = user.getEtat_disponibilité();
            Integer poidsmax = user.getPoidLivreurmax();
            Integer poidsInitial =0;
            System.out.println("*****"+poidsInitial);
            poidsInitial = deliveryRepository.getpoidsdeliveries(user.getIduser(),delivery.getDeliveryDate(),delivery.getDeliveryType(),user.getAdress_livraison());
            if (etat = true) {
                if( (delivery.getDeliveryType() == DeliveryType.Normal)&& (delivery.getEtat()==0) ){
                    if ((delivery.getPoids()+ poidsInitial) <= poidsmax*0.5) {
                       // System.out.println("++++"+poidsmax*0.5);
                        //System.out.println("*****"+poidsInitial);
                       if (delivery.getPoids()<=1){
                            delivery.setPrixLivraison(7);
                        }else {
                            delivery.setPrixLivraison(delivery.getPoids()*7);
                        }
                        delivery.setUser(user);
                        delivery.setEtat(1);
                        deliveryRepository.save(delivery);}
                     else {
                        delivery.setUser(user);
                        delivery.setEtat(1);
                     if (delivery.getPoids()<=1){
                            delivery.setPrixLivraison(7);
                        }else {
                            delivery.setPrixLivraison(delivery.getPoids()*7);
                        }
                        delivery.setDeliveryDate(delivery.getDeliveryDate().plusDays(1));
                        deliveryRepository.save(delivery);
                        System.out.println("la livraison  normal est retarde par un jour");
                    }
                }

                if ((delivery.getDeliveryType() == DeliveryType.Express)&& (delivery.getEtat()==0)) {
                    if ((delivery.getPoids() + poidsInitial) <= poidsmax ) {
                        delivery.setUser(user);
                        if (delivery.getPoids()<=1){
                            delivery.setPrixLivraison(7);
                        }else {
                            delivery.setPrixLivraison(delivery.getPoids()*7);
                        }
                        delivery.setEtat(1);
                        deliveryRepository.save(delivery);}
                    else{
                        delivery.setDeliveryType(DeliveryType.Express);
                        delivery.setUser(user);
                        if (delivery.getPoids()<=1){
                            delivery.setPrixLivraison(9);
                        }else {
                            delivery.setPrixLivraison(delivery.getPoids()*9);
                        }
                        delivery.setEtat(1);
                        delivery.setDeliveryDate(delivery.getDeliveryDate().plusDays(1));
                        deliveryRepository.save(delivery);

                        System.out.println("la livraison  expess est retarde par un jour2");

                }}

            }
        }

        }





    @Override
    public List<Delivery> Chercherdeliveriespourcejour(Integer id, LocalDate date) {

        return deliveryRepository.findAllByUser_IduserAndDeliveryDate(id,date);
    }




    @Override
    public Integer poidsdeliveries2(Integer id, LocalDate date, DeliveryType type, String adress) {
        return deliveryRepository.getpoidsdeliveries(id,date,type,adress);
    }


    @Override
    public List<Delivery> retrieveAllLivraisonslivrés() {
        return deliveryRepository.findAllByDeliveryStatusIsLike(DeliveryStatus.Delivered);
    }







        }









    /*@Override
    public Contrat affectContratToEtudiant(Integer idContrat, String nomE, String prenomE) {
        Contrat c = Cr.findById(idContrat).orElse(null);


        System.out.println(nomE);

        System.out.println(prenomE);
        Set<Etudiant> listE =Er.findByNomELikeAndPrenomELike(nomE,prenomE);
        System.out.println(listE);
        int contratactif = 0;
        for(Etudiant etudiant: listE) {
            Set<Contrat> ContratEtudiant =  etudiant.getContrat();
            for(Contrat cont :ContratEtudiant) {
                if (cont.getArchive() == false){

                    contratactif++;
                }
            }
            if (contratactif<5){
                c.setEtudiant(etudiant);

            }

            Cr.save(c);
        }

        return null;
    }*/

