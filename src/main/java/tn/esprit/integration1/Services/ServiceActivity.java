package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Activity;
import tn.esprit.integration1.Entities.ActivityType;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Interfaces.IActivity;
import tn.esprit.integration1.Repositories.ActivityRepository;
import tn.esprit.integration1.Repositories.CenterRepository;
import tn.esprit.integration1.Repositories.UserRepository;



import javax.xml.stream.events.StartDocument;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ServiceActivity implements IActivity {
    @Autowired
    ActivityRepository Ar;
    @Autowired
    CenterRepository Cr;
    @Autowired
    UserRepository Ur;
    @Override
    public List<Activity> retrieveAllActivities() {
        return Ar.findAll();
    }

    @Override
    public Activity addActivity(Activity a) {
        return Ar.save(a);
    }

    @Override
    public Activity updateActivity(Activity a) {
        return Ar.save(a);
    }

    @Override
    public Activity retrieveActivity(Integer idActivity) {
        return Ar.findById(idActivity).orElse(null);
    }

    @Override
    public void removeActivity(Integer idActivity) {
        Ar.deleteById(idActivity);

    }

    @Override
    public void assignActivityToCenter(Integer idActivity, Integer idcenter) {
        Center C =  Cr.findById(idcenter).orElse(null);
       Activity A = Ar.findById(idActivity).orElse(null);
        A.setCenter(C);
        Ar.save(A);

    }

    @Override
    public List<Activity> searchactivities( ActivityType activityType) {
        return Ar.FiltreActivity(activityType);
    }

}
