package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Tour;
import tn.esprit.integration1.Entities.TourReservation;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.TourReservationInterface;
import tn.esprit.integration1.Repositories.TourRepository;
import tn.esprit.integration1.Repositories.TourReservationRepository;
import tn.esprit.integration1.Repositories.UserRepository;


import java.util.List;

@Service
public class TourResrvationService implements TourReservationInterface {
    @Autowired
    TourReservationRepository tourReservationRepository;

    @Autowired
    TourRepository tourRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<TourReservation> retrieveAllToursReservation() {
        return tourReservationRepository.findAll();
    }

    @Override
    public TourReservation updateTourReservation(TourReservation ce) {
        return tourReservationRepository.save(ce);
    }

    @Override
    public TourReservation addTourReservation(TourReservation ce) {
        return tourReservationRepository.save(ce);
    }

    @Override
    public TourReservation retrieveTourReservation(Integer idTour) {
        return tourReservationRepository.findById(idTour).orElse(null);
    }

   /* @Override
    public void AffectReservationToTour(String tourRes) {

        Tour tour = tourRepository.retrievetour(tourRes);
        Integer nbR =tour.getNbR();
        Integer nbD =tour.getNbD();
        List<TourReservation> tourReservations =tourReservationRepository.findAllByTourResIsLike(tourRes);
        for (TourReservation tourReservation : tourReservations) {
            Integer nbAreserver =tourReservation.getNbareserver();
            if (nbD>=nbAreserver)
        {tourReservation.setTour(tour);
            tourReservationRepository.save(tourReservation);
            tour.setNbR(nbR+nbAreserver);
            tour.setNbD(nbD-nbAreserver);
        }System.out.println("pas de place disponible");
        }
    }
*/



    /*public void Reserver2(Integer iduser, Integer idTour,Integer nbAreserver) {
        Tour tour = tourRepository.findById(idTour).orElse(null);
        User u = userRepository.findById(iduser).orElse(null);
        Integer nbR =tour.getNbR();
        Integer nbD =tour.getNbD();
          TourReservation reservation =new TourReservation();
            reservation.setTour(tour);
            reservation.setUser(u);
            tourReservationRepository.save(reservation);
            tour.setNbR(nbR + nbAreserver);
            tour.setNbD(nbD - nbAreserver);
            tourRepository.save(tour);
        }

*/



        public void Reserver(Integer iduser, Integer idTour,Integer nbAreserver) {
                Tour tour = tourRepository.findById(idTour).orElse(null);
                User u = userRepository.findById(iduser).orElse(null);
               // TourReservation reservation =new TourReservation();
                List<TourReservation> tourReservations =(tourReservationRepository.findAll());

                if (tourReservations.isEmpty()){
                    TourReservation reservation =new TourReservation();
                            reservation.setTour(tour);
                            reservation.setUser(u);
                            reservation.setNbareserver(nbAreserver);
                            reservation.setDurationR(tour.getDuration());
                            reservation.setLocation(tour.getLocationName());
                            reservation.setGuide(tour.getUser().getFirstname());
                            tourReservationRepository.save(reservation);
                            for ( int i=0;i<=(nbAreserver-1) ;i++){
                             tour.setNbR(tour.getNbR()+1);
                                tour.setNbD(tour.getNbD()-1);

                        }
                         tourRepository.save(tour);}
                else {
                for (TourReservation tr: tourReservations) {
                    if (tr.getTour().getIdTour() == idTour) {
                        if (tour.getNbD() > nbAreserver) {
                            TourReservation reservation =new TourReservation();
                            reservation.setTour(tour);
                            reservation.setUser(u);
                            reservation.setNbareserver(tour.getNbR());
                            reservation.setDurationR(tour.getDuration());
                            reservation.setLocation(tour.getLocationName());
                            reservation.setGuide(tour.getUser().getFirstname());
                            tourReservationRepository.save(reservation);
                           for ( int i=0;i<=(nbAreserver-1) ;i++){
                                tour.setNbR(tour.getNbR()+1);
                                tour.setNbD(tour.getNbD()-1);
                            }
                           tourRepository.save(tour);

                        } else {
                            System.out.println("pas de place disponible");
                        }
                    }
                }
                }
    }

                    @Override
    public void removeTourReservation(Integer idTour) {
        tourReservationRepository.deleteById(idTour);
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
    }