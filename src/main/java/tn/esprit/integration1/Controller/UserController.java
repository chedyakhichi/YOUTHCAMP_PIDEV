package tn.esprit.integration1.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.integration1.Services.UserServiceImpl;

@Tag(name = "User Management")
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserServiceImpl userService;


}
