package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.Entities.User;

public interface IUserService {
     User registerUser(User user) ;
     public void AffecterUserToPublication(Integer idPub, Integer iduser);
}
