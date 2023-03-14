package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Activity;
import tn.esprit.integration1.Entities.ActivityType;
import tn.esprit.integration1.Services.ServiceActivity;


import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Activity")
public class ActivityRestController {
 ServiceActivity Sa;

    @GetMapping("/retrieve-all-Activities")
    public List<Activity> getActivities() {
        List<Activity> listActivities = Sa.retrieveAllActivities();
        return listActivities;
    }

    @GetMapping("/retrieve-Activity/{activity-id}")
    public Activity retrieveActivity (@PathVariable("activity-id") Integer idActivity) {
        return Sa.retrieveActivity(idActivity);
    }

    @PostMapping("/add-Activity")
    public Activity addActivity(@RequestBody Activity a) {
        Activity activity = Sa.addActivity(a);
        return activity;
    }
    @PutMapping("/update-activity")
    public Activity updateCenter(@RequestBody Activity a ) {
        Activity activity = Sa.updateActivity(a);
        return activity ;
    }
    @DeleteMapping("/remove-Activity/{Activity-id}")
    public void removeOperateur(@PathVariable("Activity-id") Integer AId) {
        Sa.removeActivity(AId);
    }
    @PutMapping("/assi/{center-id}/{activity-id}")
    public void AffecterActivityToCenter(@PathVariable("center-id") Integer idC , @PathVariable("activity-id") Integer idA)
    {
        Sa.assignActivityToCenter(idC,idA);

    }
    @GetMapping("/search/{activityType}")
    public List<Activity> searchActivities(
            @PathVariable("activityType") ActivityType activityType) {
        List<Activity> activities = Sa.searchactivities(activityType);
        return activities;
    }

}
