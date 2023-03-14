package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.ReviewC;
import tn.esprit.integration1.Services.ServiceReview;

@RestController
@AllArgsConstructor
@RequestMapping("/Review")
public class ReviewRestController {
 ServiceReview Sr;


    @PostMapping("/apply/{center-id}/{user-id}")
    public ReviewC apply(@RequestBody ReviewC r, @PathVariable("center-id") Integer idC , @PathVariable("user-id") Integer idU) {
        ReviewC R = Sr.apply(r,idC,idU);
        return R;
    }

}
