package tn.esprit.integration1.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Services.UserServiceImpl;

@Tag(name = "User Management")
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    //Affectation User->Publication
    @PutMapping(value="/affecter-User-Publication/{Iduser}/{publicationId}")
    public void affecterUserToPublication(@PathVariable("Iduser") Integer iduser,
                                          @PathVariable("publicationId")Integer idPub){
        userService.AffecterUserToPublication(iduser,idPub );
    }
    //Ajouter user
    @PostMapping("/add-user")
    public User addUser(@RequestBody User u) {
        User user = userService.registerUser(u);
        return user;
    }
    @PutMapping("/assi/{center-id}/{user-id}")
    public void AffecterUserCenter (@PathVariable("center-id") Integer idC , @PathVariable("user-id") Integer idU)
    {
        userService.assignUserToCenter(idU,idC);

    }

    @PutMapping("/affect/{userid}/{reservationid}")
    public void AffecterUserReservation (@PathVariable Integer userid,@PathVariable Integer reservationid )
    {   System.out.println("Hello -- i'm here");
        userService.assignUserToReservation(userid, reservationid);

    }

}
