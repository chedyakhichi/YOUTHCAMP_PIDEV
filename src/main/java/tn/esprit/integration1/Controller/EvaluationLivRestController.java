package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.EvaluationLiv;
import tn.esprit.integration1.Entities.EvaluationType;
import tn.esprit.integration1.Services.EvaluationLivService;


import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/User")

public class EvaluationLivRestController {
    EvaluationLivService evaluationLivService;
  @PostMapping("/add-EvaluationLiv")
  public EvaluationLiv addEvaluationLiv (@RequestBody @Valid EvaluationLiv c) {
    EvaluationLiv E =evaluationLivService.addEvaluation(c);
    return E;
  }

  @PutMapping(value = "/evaluerLivraison/{userid}/{livraisonid}/{evaluation}/{text}")
  public void affectReservationToUser(@PathVariable ("userid") Integer userid,
                                      @PathVariable ("livraisonid") Integer livraisonid,
                                      @PathVariable ("evaluation") EvaluationType e,
                                      @PathVariable ("text") String t){

   evaluationLivService.AffecterEvaluation(userid,livraisonid,e,t);
  }



/*
  @DeleteMapping("/remove-Tour/{Tour-id}")
  public void removeTour(@PathVariable("Tour-id") Integer eId) {

    tourService.removeTour(eId);
  }


  @PutMapping("/update-Tour")
  public Tour updateTour(@RequestBody Tour c) {
    Tour tour= tourService.updateTour(c);
    return tour;
  }


    @PutMapping(value = "/affectContratToEtudiant(/{role}")
    public Tour affectContratToEtudiant(@PathVariable ("role") String role,
                                        @RequestBody Tour tour){

        return 	(tourService.affectContratToEtudiant(role,tour));
    }


 /* @PutMapping("/affectContratToetudiant/{Tour-id}/{nomE}/{prenomE}")
  public Contrat affectContratToetudiant(@PathVariable("contrat-id") Integer contratId,
                                         @PathVariable("nomE") String nom,
                                         @PathVariable("prenomE") String prenom ) {

    return contratService.affectContratToEtudiant(contratId,nom,prenom);
  }*/


}
