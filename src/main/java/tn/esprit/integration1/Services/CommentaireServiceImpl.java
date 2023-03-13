package tn.esprit.integration1.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Commentaire;
import tn.esprit.integration1.Entities.MotInterdit;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.ICommentaire;
import tn.esprit.integration1.Repositories.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentaireServiceImpl implements ICommentaire {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    MotInterditRepository motInterditRepository;
    @Autowired
    MotInterditService ms;

    @Override
    public List<Commentaire> retrieveAllCommentaire() {
        return (List<Commentaire>)commentaireRepository.findAll();
    }

    public Commentaire addCommentaire(Commentaire c) {
        ///// detecte les mots interdits en comment-text/////////
        String d;
        d=filter(c.getCommentText());
        log.info(d);
        c.setCommentText(d);
        /////////////////////////////////////////
        return commentaireRepository.save(c);
    }

    public Commentaire updateCommentaire(Commentaire c) {
        return commentaireRepository.save(c);
    }

    public Commentaire retrieveCommentaire (Integer idCom){
        return commentaireRepository.findById(idCom).get();
    }

    @Override
  public void deleteCommentaire(Integer idCom){
        Commentaire c =retrieveCommentaire(idCom);
        commentaireRepository.delete(c);
    }

    @Override
    public void AffecterPublicationToCommentaire(String comment, Integer idPub, Integer iduser) {
        Publication p = publicationRepository.findById(idPub).orElse(null);
        User u = userRepository.findById(iduser).orElse(null);
        Commentaire c= new Commentaire();
        Integer x= p.getNbrcomment();
        for (int i=0;i<1;i++){
            x++;
        }
        p.setNbrcomment(x);
        c.setUser(u);
        c.setPubreact(p);
        c.setCommentDate(LocalDate.now());
        c.setCommentText(comment);
        commentaireRepository.save(c);
    }


    //Mot Interdit aal commentaire
    public String filter (String input){
        List<MotInterdit> mt = (List<MotInterdit>) motInterditRepository.findAll();
        String filteredInput=input;
        for (MotInterdit badWords: mt){
            filteredInput=filteredInput.replaceAll(badWords.getMot(), "****" );
        }return filteredInput;
    }



    public List<Publication> Top3Pub() {

        List<Publication> topPost = (List<Publication>) publicationRepository.findAll();
        List<Publication> result = topPost.stream()
                .sorted(Comparator.comparingInt(Publication::getNbrcomment).reversed())
                .limit(3)
                .collect(Collectors.toList());
        return result;
    }



}
