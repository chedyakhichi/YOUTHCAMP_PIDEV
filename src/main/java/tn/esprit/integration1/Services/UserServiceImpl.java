package tn.esprit.integration1.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.IUserService;
import tn.esprit.integration1.Repositories.PublicationRepository;
import tn.esprit.integration1.Repositories.UserRepository;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PublicationRepository publicationRepository;


    @Override
    public User registerUser(User user) {
        // Assign loyalty points to the new user
        user.setLoyaltyPts(100f);

        // Save the new user to the database
        userRepository.save(user);
        return user;
    }
    //Affectation UserToPublication (chedya)
    public void AffecterUserToPublication(Integer idPub, Integer iduser){
        User u= userRepository.findById(iduser).orElse(null);
        Publication p= publicationRepository.findById(idPub).orElse(null);
        u.getPublications().add(p);
        userRepository.save(u);
    }
}
