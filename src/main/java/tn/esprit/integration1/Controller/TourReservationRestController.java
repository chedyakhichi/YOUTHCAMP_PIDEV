package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.TourReservation;
import tn.esprit.integration1.Services.TourResrvationService;
import tn.esprit.integration1.Services.UserServiceImpl;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/TourReservation")

public class TourReservationRestController {

 TourResrvationService tourResrvationService ;
 UserServiceImpl userService;

  @GetMapping("/retrieve-all-ToursReservation")
  public List<TourReservation> getToursReservation() {
    List<TourReservation> listTours = tourResrvationService.retrieveAllToursReservation();
    return listTours;
  }


  @PostMapping("/add-TourReservation")
  public TourReservation addTourReservation (@RequestBody TourReservation c) {
    TourReservation tour = tourResrvationService.addTourReservation(c);
    return tour;
  }

  @DeleteMapping("/remove-TourReservation/{Tour-id}")
  public void removeTourReservation(@PathVariable("Tour-id") Integer eId) {

    tourResrvationService.removeTourReservation(eId);
  }


  @PutMapping("/update-TourReservation")
  public TourReservation updateTourReservation(@RequestBody TourReservation c) {
    TourReservation tour= tourResrvationService.updateTourReservation(c);
    return tour;
  }

   /* @PutMapping(value = "/affectTourreservationTotour(/{tour}")
    public void affectReservationToTour(@PathVariable ("tour") String tour){

        tourResrvationService.AffectReservationToTour(tour);
    }*/

   @PutMapping(value = "/reserver/{userid}/{tourid}/{nbvalider}")
    public void affectReservationToUser(@PathVariable ("userid") Integer userid,
                                        @PathVariable ("tourid") Integer tourid,
                                         @PathVariable("nbvalider")Integer nbvalider){

        tourResrvationService.Reserver(userid,tourid,nbvalider);
    }

   /* @PutMapping(value = "/reserver2/{userid}/{tourid}/{nbvalider}")
    public void affectReservationToUser2(@PathVariable ("userid") Integer userid,
                                        @PathVariable ("tourid") Integer tourid,
                                        @PathVariable("nbvalider")Integer nbvalider){

        tourResrvationService.Reserver2(userid,tourid,nbvalider);
    }
*/



 /* @PutMapping("/affectContratToetudiant/{Tour-id}/{nomE}/{prenomE}")
  public Contrat affectContratToetudiant(@PathVariable("contrat-id") Integer contratId,
                                         @PathVariable("nomE") String nom,
                                         @PathVariable("prenomE") String prenom ) {

    return contratService.affectContratToEtudiant(contratId,nom,prenom);
  }*/
}
