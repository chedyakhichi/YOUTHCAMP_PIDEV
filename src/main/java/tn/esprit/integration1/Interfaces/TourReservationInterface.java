package tn.esprit.integration1.Interfaces;

import tn.esprit.integration1.Entities.TourReservation;


import java.util.List;

public interface TourReservationInterface {
    List<TourReservation> retrieveAllToursReservation();

    TourReservation updateTourReservation(TourReservation ce);

    TourReservation addTourReservation(TourReservation ce);

    TourReservation retrieveTourReservation(Integer idTourReservation);
    //public void affectreservationToTour(String tour);
   // public void AffectReservationToTour(String tourRes);
    public void Reserver(Integer iduser, Integer idTour,Integer nbAreserver);
    void removeTourReservation(Integer idTourReservation);
}
