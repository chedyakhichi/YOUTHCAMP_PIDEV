package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Services.ServiceCenter;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Center")
public class CenterRestController {
 ServiceCenter Sc;

    @GetMapping("/retrieve-all-Centers")
    public List<Center> getCenters() {
        List<Center> listCenters = Sc.retrieveAllCenters();
        return listCenters;
    }

    @GetMapping("/retrieve-Center/{center-id}")
    public Center retrieveCenter(@PathVariable("center-id") Integer idcenter) {
        return Sc.retrieveCenter(idcenter);
    }

    @PostMapping("/add-Center")
    public Center addCenter(@RequestBody Center c) {
        Center center = Sc.addCenter(c);
        return center;
    }
    @PutMapping("/update-center")
    public Center updateCenter(@RequestBody Center c ) {
        Center center = Sc.updateCenter(c);
        return center ;
    }
    @DeleteMapping("/remove-Center/{Center-id}")
    public void removeOperateur(@PathVariable("Center-id") Integer CId) {
        Sc.removeCenter(CId);
    }
    @PutMapping("/assi/{center-id}/{location-id}")
    public void AffecterActivityToCenter(@PathVariable("center-id") Integer idC , @PathVariable("location-id") Integer idl)
    {
        Sc.assignCenterToLocation(idC,idl);

    }
    @GetMapping("/nbRatingCenter/{id-center}")
    public Integer nbRatingCenter(@PathVariable("id-center")  Integer CId) {
        return Sc.nombreRatingCenter(CId);
    }
    @GetMapping("/sortedByPrice")
    public List<Center> getAllProductsSortedByPrice() {

        return Sc.getAllCentersSortedByPrice();
    }
   // @Scheduled(cron="*/5 * * * * *")
    @GetMapping("/Statistiques/")
    public void Statistique() {
      Sc.statistiques();
    }
}
