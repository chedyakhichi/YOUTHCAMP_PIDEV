package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Tour;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.TourInterface;
import tn.esprit.integration1.Repositories.TourRepository;
import tn.esprit.integration1.Repositories.UserRepository;


import java.util.List;

@Service
public class TourService implements TourInterface {
    @Autowired
    TourRepository tourRepository;

    @Autowired
    UserRepository userRepository ;
    @Override
    public List<Tour> retrieveAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public Tour updateTour(Tour ce) {
        return tourRepository.save(ce);
    }

    @Override
    public Tour addTour(Tour ce) {
        return tourRepository.save(ce);
    }

    @Override
    public Tour retrieveTour(Integer idTour) {
        return tourRepository.findById(idTour).orElse(null);
    }

    @Override
    public void removeTour(Integer idTour) {
          tourRepository.deleteById(idTour);
    }



    public void affectTourToUser(Integer tourId, Integer userId) {
        Tour tour =tourRepository.findById(tourId).orElse(null);
        User e = userRepository.findById(userId).orElse(null);
        assert tour != null;
        tour.setUser(e);
        tourRepository.save(tour);

    }




}
