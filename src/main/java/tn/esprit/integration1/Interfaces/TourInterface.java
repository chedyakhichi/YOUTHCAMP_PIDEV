package tn.esprit.integration1.Interfaces;

import tn.esprit.integration1.Entities.Tour;


import java.util.List;

public interface TourInterface {
    List<Tour> retrieveAllTours();

    Tour updateTour(Tour ce);

    Tour addTour(Tour ce);

    Tour retrieveTour(Integer idTour);

    void removeTour(Integer idTour);
}
