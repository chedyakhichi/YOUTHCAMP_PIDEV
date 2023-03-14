package tn.esprit.integration1.Interfaces;
import tn.esprit.integration1.Entities.Activity;
import tn.esprit.integration1.Entities.ActivityType;


import java.time.LocalDate;
import java.util.List;

public interface IActivity {
    List<Activity> retrieveAllActivities();

    Activity addActivity(Activity a);

    Activity updateActivity (Activity a);

    Activity retrieveActivity (Integer idActivity);
    void removeActivity(Integer idActivity);
    public void assignActivityToCenter (Integer idActivity, Integer idcenter) ;
  public List<Activity>  searchactivities( ActivityType activityType);
}
