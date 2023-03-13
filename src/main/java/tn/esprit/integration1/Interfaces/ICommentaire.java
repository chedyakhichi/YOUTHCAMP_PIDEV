package tn.esprit.integration1.Interfaces;

import tn.esprit.integration1.Entities.Commentaire;
import tn.esprit.integration1.Entities.Publication;

import java.util.List;

public interface ICommentaire {
    public List<Commentaire> retrieveAllCommentaire();

    public Commentaire addCommentaire (Commentaire c);

    public Commentaire updateCommentaire (Commentaire c);

    public Commentaire retrieveCommentaire (Integer idCom);

    public void deleteCommentaire(Integer idCom);
    //Afferctation Pub to Commentaire
    public void AffecterPublicationToCommentaire(String comment, Integer idPub, Integer iduser);
    public List<Publication> Top3Pub();

}
