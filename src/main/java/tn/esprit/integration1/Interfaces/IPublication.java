package tn.esprit.integration1.Interfaces;



import org.springframework.web.multipart.MultipartFile;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.TypePublication;

import java.io.IOException;
import java.util.List;

public interface IPublication {
   /* public List<Publication> retrieveAllPublication();

    */

    public Publication addPublication (String pub, MultipartFile image) throws IOException;

    public Publication updatePublication (Publication p);

    public  void deletePublication(Integer idPub);

    Publication retrievePublication(Integer idPub);

    List <Publication> RecherchePublication(TypePublication t);
    //public Publication MostLiked();

}
