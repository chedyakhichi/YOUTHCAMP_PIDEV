package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.Location;
import tn.esprit.integration1.Services.ServiceLocation;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Location")
public class LocationRestController {
    ServiceLocation Sl;

    @GetMapping("/retrieve-all-Locations")
    public List<Location> getLocations() {
        List<Location> listLocations = Sl.retrieveAllLocations();
        return listLocations;
    }

    @GetMapping("/retrieve-Location/{location-id}")
    public Location retrieveLocation(@PathVariable("location-id") Integer idLocation) {
        return Sl.retrieveLocation(idLocation);
    }

    @PostMapping("/add-Location")
    public Location addLocation(@RequestBody Location l) {
        Location location = Sl.addLocation(l);
        return location;
    }

    @PutMapping("/update-Location")
    public Location updateLocation(@RequestBody Location l) {
        Location location = Sl.updateLocation(l);
        return location;
    }

    @DeleteMapping("/remove-Location/{Location-id}")
    public void removeOperateur(@PathVariable("Location-id") Integer idL) {
        Sl.removeLocation(idL);
    }

    @GetMapping("/SuggestionCenter/{user-id}")
    public List<Center> SuggestionCenter(@PathVariable("user-id") Integer id) {
        List<Center> listCenters = Sl.SuggestionLocation(id);
        return listCenters;
    }
}