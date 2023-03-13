package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.TypePublication;
import tn.esprit.integration1.Repositories.ReactionRepository;
import tn.esprit.integration1.Services.PublicationServiceImpl;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/Publication")
public class PublicationRestController {
    @Autowired
    ReactionRepository Reactionrepo;
    @Autowired
    PublicationServiceImpl publicationService;


   //Afficher les pubs
    @GetMapping("/recherche by type publication/{typePub}")
    public List<Publication> RecherchePubByType(@PathVariable("typePub") TypePublication typePub) {

        return publicationService.RecherchePublication(typePub);
    }


 //
    @GetMapping("/retrieve-publication/{publication-id}")
    public Publication retrievePublication(@PathVariable("publication-id") Integer idPub) {
        return publicationService.retrievePublication(idPub);
    }

  //Ajouter pub

    @PostMapping(path = "/add-publication",consumes = {MULTIPART_FORM_DATA_VALUE})
    public Publication addPublication(@RequestParam String request,@RequestParam MultipartFile file) throws IOException {
        Publication publication = publicationService.addPublication(request,file);
        return publication;
    }

    // supprimer pub
    @DeleteMapping("/remove-Publication/{publication}")
    public void deletePublication(@PathVariable("publication") Integer idPub) {
        publicationService.deletePublication(idPub);
    }

//Modifier
   @PutMapping("/update-Publication")
    public Publication updatePublication(@RequestBody Publication p) {
        Publication publication= publicationService.updatePublication(p);
        return publication;
    }
}


