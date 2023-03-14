package tn.esprit.integration1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Feedback;
import tn.esprit.integration1.Interfaces.IFeedbackService;

import java.util.List;

@RestController
@RequestMapping("/Feedback")

public class FeedbackController {
@Autowired
IFeedbackService feedbackService;


    @PostMapping("/AddFeedback")
    public Feedback addFeedback(@RequestBody Feedback F) {
        Feedback feedback = feedbackService.addFeedback(F);
        return feedback;
    }

    @GetMapping("/retrieve-all-feedbacks")
    public List<Feedback> getFeedbacks() {
        List<Feedback> listFeedbacks = feedbackService.retrieveAllFeedbacks();
        return listFeedbacks;
    }

    @PutMapping("/update-feedback")
    public Feedback updateFeedback(@RequestBody Feedback c) {
        Feedback feedback = feedbackService.updateFeedback(c);
        return feedback;
    }
    @DeleteMapping("/remove-feedback/{idFeedback}")
    public void removeFeedback(@PathVariable("idFeedback") Integer idFeedback) {

        feedbackService.removeFeedback(idFeedback);
    }
    @PutMapping("/assign-Feedback/{idFeedback}/{IdUser}")
    public void assignFeedbackToUser(@PathVariable("idFeedback") Integer idFeedback,
                                     @PathVariable("IdUser") Integer IdUser) {
        feedbackService.assignFeedbackToUser(idFeedback,IdUser);

    }
}
