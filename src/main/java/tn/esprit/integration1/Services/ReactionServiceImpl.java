package tn.esprit.integration1.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.Reaction;
import tn.esprit.integration1.Entities.TypeReaction;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.IReaction;
import tn.esprit.integration1.Repositories.PublicationRepository;
import tn.esprit.integration1.Repositories.ReactionRepository;
import tn.esprit.integration1.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ReactionServiceImpl implements IReaction {
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Reaction> retrieveAllReaction() {
        return null;
    }

    @Override
   /*  List<Publication> retrieveAllPublication(){
        return reactionRepository.findAll();
    }

    public List<Reaction> retrieveAllReaction(){
        return (List<Publication>)reactionRepository.findAll();
    }*/

    public Reaction addReaction (Reaction r){
        return reactionRepository.save(r);
    }

    public  Reaction updateReaction(Reaction r)
    {
        return reactionRepository.save(r);
    }

    public Reaction retrieveReaction (Integer idReac){
        return reactionRepository.findById(idReac).get();
    }
    public void deleteReaction(Integer idReac){
        Reaction r=retrieveReaction(idReac);
        reactionRepository.delete(r);
    }
    //Affectation PubToReaction
    public void AffecterPublicationToReaction(TypeReaction Treact, Integer idPub, Integer iduser){
        Publication p= publicationRepository.findById(idPub).orElse(null);
        User u = userRepository.findById(iduser).orElse(null);
        List<Reaction> rec= reactionRepository.ReactionByUser(iduser,idPub);
        if (rec.isEmpty()) {
            Reaction r = new Reaction();
            r.setUser(u);
            r.setPubreact(p);
            r.setTypeReaction(Treact);
            reactionRepository.save(r);
        }else {
        for (Reaction re: rec) {
           if (!reactionRepository.ReactionByUser(iduser,idPub).isEmpty()) {
         //   if (re.getPubreact().getIdPub()==idPub){
                System.out.println("you can't add another reaction");
            }else {
            Reaction r = new Reaction();
            r.setUser(u);
            r.setPubreact(p);
            r.setTypeReaction(Treact);

        }}}
    }

    @Override
    public Publication MostReaction(TypeReaction t) {
        List<Publication> publications = (List<Publication>) publicationRepository.findAll();
        List<Integer> lenghts = new ArrayList<Integer>();

        for(Publication p : publications){
            Integer nbreaction = reactionRepository.MostReactionn(p,t);
            lenghts.add(nbreaction);
        }
        int n = Collections.max(lenghts);
        for(Publication p : publications){
            Integer nbreaction = reactionRepository.MostReactionn(p,t);
            if (n== nbreaction)
            {
                return p ;
            }
        }
        return null;
    }







    @Override
    public Publication TopPublication() {
        List<Publication> publications = (List<Publication>) publicationRepository.findAll();
        List<Integer> lenghts = new ArrayList<Integer>();

        for(Publication p : publications){
            Integer nbreaction = reactionRepository.TopReaction(p);
            lenghts.add(nbreaction);
        }
        int n = Collections.max(lenghts);
        for(Publication p : publications){
            Integer nbreaction = reactionRepository.TopReaction(p);
            if (n== nbreaction)
            {
               return p;
            }
        }
        return null;
    }


}
