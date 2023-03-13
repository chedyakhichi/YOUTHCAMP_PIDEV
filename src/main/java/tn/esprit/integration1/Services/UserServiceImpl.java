package tn.esprit.integration1.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.IUserService;
import tn.esprit.integration1.Repositories.UserRepository;

import java.math.BigDecimal;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public void registerUser(User user) {
        // Assign loyalty points to the new user
        user.setLoyaltyPts(100f);

        // Save the new user to the database
        userRepository.save(user);
    }
}

