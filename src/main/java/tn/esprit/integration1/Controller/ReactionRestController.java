package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.Reaction;
import tn.esprit.integration1.Entities.TypeReaction;
import tn.esprit.integration1.Interfaces.IReaction;
import tn.esprit.integration1.Repositories.PublicationRepository;
import tn.esprit.integration1.Repositories.ReactionRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Reaction")
public class ReactionRestController {
    IReaction reactionService;

    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private PublicationRepository publicationRepository;

    @PutMapping(value="/affecter-Publication-Reaction/{TypeReaction}/{PubId}/{UserId}")
    public void affecterPublicationToReaction(@PathVariable("TypeReaction") TypeReaction Treact,
                                              @PathVariable("PubId")Integer idPub,
                                          @PathVariable("UserId")Integer iduser)
    {

        reactionService.AffecterPublicationToReaction(Treact,idPub,iduser);
    }

    //Afficher les Reaction
    @GetMapping("/retrieve-all-reation")
    public List<Reaction> getReactions() {
        List<Reaction> reactionList = reactionService.retrieveAllReaction();
        return reactionList;
    }
    //
    @GetMapping("/retrieve-reaction/{reaction-id}")
    public Reaction retrieveReaction(@PathVariable("reaction-id") Integer idReac) {
        return reactionService.retrieveReaction(idReac);
    }
    //Ajouter reaction
    @PostMapping("/add-reaction")
    public Reaction addReaction(@RequestBody Reaction r) {
        Reaction reaction = reactionService.addReaction(r);
        return reaction;
    }
    // supprimer reac
    @DeleteMapping("/remove-Reaction/{reaction}")
    public void deleteReaction(@PathVariable("reaction") Integer idReac) {
        reactionService.deleteReaction(idReac);
    }
    //Modifier
    @PutMapping("/update-Reaction")
    public Reaction updateReaction(@RequestBody Reaction r) {
        Reaction reaction= reactionService.updateReaction(r);
        return reaction;
    }

    //MostReaction

    @GetMapping("/MostReaction/{TypeReaction}")
    public Publication MostReaction(@PathVariable("TypeReaction") TypeReaction t) {
        Publication p= reactionService.MostReaction(t);
        return p;
    }
    //@Scheduled(cron ="*/30 * * * * *")
    //Top Publication
    @GetMapping("/TopPublication/")
    public Publication TopPublication() {
        Publication p= reactionService.TopPublication();
        System.out.println("the most reacted publication in this hour is ");
      System.out.println(p);
        return p;
    }

}
