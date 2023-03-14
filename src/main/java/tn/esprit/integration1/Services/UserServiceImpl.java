package tn.esprit.integration1.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.Publication;
import tn.esprit.integration1.Entities.Reservation;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.IUserService;
import tn.esprit.integration1.Repositories.*;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    CenterRepository Cr;
    @Autowired
    ReservationRepository Rr;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    private JavaMailSender mailSender; // Autowired mail sender for sending emails

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
    @Override
    public void assignUserToCenter(Integer iduser, Integer idcenter) {
        Center C =  Cr.findById(idcenter).orElse(null);
        User U = userRepository.findById(iduser).orElse(null);
        U.getCenters().add(C);
        userRepository.save(U);


    }

    @Override
    public void assignUserToReservation(Integer iduser, Integer idreservation) {
        Reservation R =  Rr.findById(idreservation).orElse(null);
        User U = userRepository.findById(iduser).orElse(null);
        U.getReservations().add(R);
        userRepository.save(U);
    }
    public void sendLoyaltyPointsReminder(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Reminder: Your Loyalty Points are Expiring Soon!");
        message.setText("Dear " + user.getFirstname() + ",\n\n" +
                "This is a reminder that your loyalty points are expiring soon. You currently have " +
                user.getLoyaltyPts() + " points, which will expire on " + user.getLoyaltyPointsExpireDate() +
                ". Don't forget to use them before they expire!\n\n" +
                "Thank you for being a loyal customer of our online store.\n" +
                "Best regards,\n" +
                "The Store Team");
        mailSender.send(message);
    }

}

