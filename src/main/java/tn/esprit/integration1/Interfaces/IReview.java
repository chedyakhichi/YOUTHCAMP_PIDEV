package tn.esprit.integration1.Interfaces;
import tn.esprit.integration1.Entities.ReviewC;

public interface IReview {
    public ReviewC apply(ReviewC review, Integer idcenter, Integer iduser);
}
