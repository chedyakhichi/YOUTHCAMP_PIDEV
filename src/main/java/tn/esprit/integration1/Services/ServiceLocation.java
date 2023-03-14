package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.Location;
import tn.esprit.integration1.Entities.Reservation;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.ILocation;
import tn.esprit.integration1.Repositories.CenterRepository;
import tn.esprit.integration1.Repositories.LocationRepository;
import tn.esprit.integration1.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLocation implements ILocation {
    @Autowired
    LocationRepository Lr;
    @Autowired
    CenterRepository Cr;
    @Autowired
    UserRepository Ur;

    @Override
    public List<Location> retrieveAllLocations() {
        return Lr.findAll();
    }

    @Override
    public Location addLocation(Location l) {
        return Lr.save(l);
    }

    @Override
    public Location updateLocation(Location l) {
        return Lr.save(l);
    }

    @Override
    public Location retrieveLocation(Integer idLocation) {
        return Lr.findById(idLocation).orElse(null);
    }

    @Override
    public void removeLocation(Integer idLocation) {
        Lr.deleteById(idLocation);
    }

    public List<Center> SuggestionLocation(Integer iduser) {
        List<Center> CenterSuggestion = new ArrayList<>();
        User u = Ur.findById(iduser).orElse(null);
        for (Reservation r : u.getReservations()) {
            for (Center c : Lr.CenterByLocatioType(r.getCenter().getLocation().getLocationType())) {
                CenterSuggestion.add(c);
            }
        }


        return CenterSuggestion;
    }
}
