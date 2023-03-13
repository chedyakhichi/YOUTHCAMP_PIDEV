package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Commentaire;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Interfaces.ICommentaire;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Commentaire")
public class CommentaireRestController {
    @Autowired
    ICommentaire commentaireService;

    //Afficher les Commentaires
    @GetMapping("/retrieve-all-Commentaires")
    public List<Commentaire> getCommentaire() {
        List<Commentaire> commentaireList = commentaireService.retrieveAllCommentaire();
        return commentaireList;
    }
    //
    @GetMapping("/retrieve-Commentaire/{commentaire-id}")
    public Commentaire retrieveCommentaire(@PathVariable("commentaire-id") Integer idCom) {
        return commentaireService.retrieveCommentaire(idCom);
    }

    //Ajouter commentaire
    @PostMapping("/add-commentaire")
    public Commentaire addCommentaire(@RequestBody Commentaire c) {
        Commentaire commentaire = commentaireService.addCommentaire(c);
        return commentaire;
    }

    // supprimer commentaire
    @DeleteMapping("/remove-Commentaire/{commentaire}")
    public void deleteCommentiare(@PathVariable("commentaire") Integer idCom) {
        commentaireService.deleteCommentaire(idCom);
    }

    //Modifier commentaire
    @PutMapping("/update-Commentaire")
    public Commentaire updateCommentaire(@RequestBody Commentaire c) {
        Commentaire commentaire= commentaireService.updateCommentaire(c);
        return commentaire;
    }
    @PutMapping(value="/affecter-Publication-Commentaire/{comment}/{PubId}/{UserId}")
    public void affecterPublicationToReaction(@PathVariable("comment") String comment,
                                              @PathVariable("PubId")Integer idPub,
                                              @PathVariable("UserId")Integer iduser)
    {

        commentaireService.AffecterPublicationToCommentaire(comment,idPub,iduser);
    }


    @GetMapping("/Top3Pub")
    public List<Publication> getTop3() {
        List<Publication> top3 = commentaireService.Top3Pub();
        return top3;
    }
}
