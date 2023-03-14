package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.Location;
import tn.esprit.integration1.Interfaces.ICenter;
import tn.esprit.integration1.Repositories.CenterRepository;
import tn.esprit.integration1.Repositories.LocationRepository;
import tn.esprit.integration1.Repositories.ReviewRepository;

import java.util.List;

@Service
public class ServiceCenter implements ICenter {
    @Autowired
    CenterRepository Cr;
    @Autowired
    LocationRepository Lr;
    @Autowired
    ReviewRepository Rr;
    @Override
    public List<Center> retrieveAllCenters() {
        return Cr.findAll();
    }

    @Override
    public Center addCenter(Center c) {
        return Cr.save(c);
    }

    @Override
    public Center updateCenter(Center c) {
        return Cr.save(c);
    }

    @Override
    public Center retrieveCenter(Integer idcenter) {
        return Cr.findById(idcenter).orElse(null);
    }

    @Override
    public void removeCenter(Integer idcenter) {
        Cr.deleteById(idcenter);
    }

    @Override
    public void assignCenterToLocation(Integer idcenter, Integer idLocation) {
        Center C =  Cr.findById(idcenter).orElse(null);
        Location L = Lr.findById(idLocation).orElse(null);
        C.setLocation(L);
        Cr.save(C);

    }

    @Override
    public Integer nombreRatingCenter(Integer idcenter) {
        Center C =  Cr.findById(idcenter).orElse(null);
        int i;
       i=Rr.RatingSum(idcenter)/Rr.Countuser(idcenter);
        C.setRating(i);
        Cr.save(C);
        return null;
    }

    @Override
    public List<Center> getAllCentersSortedByPrice() {
        return Cr.findAll(Sort.by("name"));

    }

    public void statistiques () {
        List<Center> Centers =Cr.findAll();
        for( Center C: Centers) {
            Integer Cm = 0, Cp = 0;
            Cm = Cr.totalMoneyCenter(C.getIdcenter());
            Cp = Cr.totalReservationCenter(C.getIdcenter());
            System.out.println(" For Center " +C.getName());
            System.out.println(" Total Money " + Cm);
            System.out.println("Total Reservation " + Cp);
        }
    }
}
