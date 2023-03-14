package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.ReviewC;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.IReview;
import tn.esprit.integration1.Repositories.CenterRepository;
import tn.esprit.integration1.Repositories.ReviewRepository;
import tn.esprit.integration1.Repositories.UserRepository;


@Service
public class ServiceReview implements IReview {
    @Autowired
    UserRepository Ur;
    @Autowired
    CenterRepository Cr;
    @Autowired
    ReviewRepository Rr;
    @Override
    public ReviewC apply(ReviewC review , Integer idcenter, Integer iduser) {
        // Retrieve the user and center from the database
        User user = Ur.findById(iduser).orElse(null);
        Center center = Cr.findById(idcenter).orElse(null);

        // Create a new review entity with the user, center, rating, and review text
        ReviewC newReview = new ReviewC();
        newReview.setUser(user);
        newReview.setCenter(center);
        newReview.setRating(review.getRating());
        newReview.setReviewText(review.getReviewText());

        // Save the new review to the database
        ReviewC savedReview = Rr.save(newReview);

        // Return the saved review
        return savedReview;
    }

}


