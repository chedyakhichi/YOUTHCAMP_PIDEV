package tn.esprit.integration1.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.integration1.Entities.MotInterdit;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.TypePublication;
import tn.esprit.integration1.Interfaces.IPublication;
import tn.esprit.integration1.Repositories.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class PublicationServiceImpl implements IPublication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    MotInterditRepository motInterditRepository;
    @Autowired
    private ObjectMapper objectMapper;

   /* public List<Publication> retrieveAllPublication(){

            List<Publication> publicationList = publicationService.retrieveAllPublication();
          / for (Publication pub: publicationList){
                pub.setNbrRecation(reactionRepository.nbReactionValides(pub));
                publicationRepository.save(pub);
            }

            return publicationList;
    }

    */
    public Publication addPublication (String pub, MultipartFile image) throws IOException {
        Publication p = objectMapper.readValue(pub, Publication.class);
        ///// detecte les mots interdits en comment-text/////////
        String d;
        d=filter(p.getContent());
        log.info(d);
        p.setContent(d);
        ////////////////////////////////////////////////////////
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        p.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        return publicationRepository.save(p);
    }

    public  Publication updatePublication (Publication p)
    {
        return publicationRepository.save(p);
    }

    public Publication retrievePublication (Integer idPub){

        return publicationRepository.findById(idPub).get();
    }
    public void deletePublication(Integer idPub){
        Publication p=retrievePublication(idPub);
        publicationRepository.delete(p);
    }

    //Mot Interdit aal publication
    public String filter (String input){
        List<MotInterdit> mt = (List<MotInterdit>) motInterditRepository.findAll();
        String filteredInput=input;
        for (MotInterdit badWords: mt){
            filteredInput=filteredInput.replaceAll(badWords.getMot(), "****" );
        }return filteredInput;
    }



    @Override
    public  List <Publication> RecherchePublication(TypePublication t) {
        List<Publication> publications =publicationRepository.PublicationByType(t);
        return publications;
    }

 }
