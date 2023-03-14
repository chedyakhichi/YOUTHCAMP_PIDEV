package tn.esprit.integration1.Interfaces;

import tn.esprit.integration1.Entities.Reservation;

import java.util.List;

public interface IReservation {
    List<Reservation> retrieveAllReservations();

    Reservation addReservation(Reservation r);

    Reservation updateReservation (Reservation r);

    Reservation retrieveReservation (Integer idReservation);
    void removeReservation(Integer idReservation);
    public void assignCenterToReservation (Integer idcenter,Integer idReservation ) ;
    public void  ReservationPrice (Integer idcenter,Integer idReservation ) ;

}
