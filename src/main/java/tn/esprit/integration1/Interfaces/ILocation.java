package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.Location;

import java.util.List;

public interface ILocation {
    List<Location> retrieveAllLocations();

    Location addLocation(Location l);

    Location updateLocation (Location l);

    Location retrieveLocation (Integer idLocation);
    void removeLocation(Integer idLocation);
    public List<Center> SuggestionLocation(Integer iduser);
}
