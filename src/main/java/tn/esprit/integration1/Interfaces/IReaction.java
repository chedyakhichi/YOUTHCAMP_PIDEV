package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.Reaction;
import tn.esprit.integration1.Entities.TypeReaction;

import java.util.List;

public interface IReaction {
    public List<Reaction> retrieveAllReaction();

    public Reaction addReaction (Reaction r);

    public Reaction updateReaction (Reaction r);

    public Reaction retrieveReaction (Integer idReac);

    public  void deleteReaction(Integer idReac);
    public void AffecterPublicationToReaction(TypeReaction Treact, Integer idPub, Integer iduser);
    public Publication MostReaction(TypeReaction t);

    Publication TopPublication();

    // public Set<Publication> retrievePublicationByReaction(Integer idReac);

}
