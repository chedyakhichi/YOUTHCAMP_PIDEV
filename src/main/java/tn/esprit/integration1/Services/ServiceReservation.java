package tn.esprit.integration1.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Center;
import tn.esprit.integration1.Entities.Reservation;
import tn.esprit.integration1.Interfaces.IReservation;
import tn.esprit.integration1.Repositories.ActivityRepository;
import tn.esprit.integration1.Repositories.CenterRepository;
import tn.esprit.integration1.Repositories.ReservationRepository;

import java.util.List;

@Service
public class ServiceReservation implements IReservation {
    @Autowired
    ReservationRepository Rr;
    @Autowired
    CenterRepository Cr;
    @Autowired
    ActivityRepository Ar;
    @Override
    public List<Reservation> retrieveAllReservations() {
        return Rr.findAll();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return Rr.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return Rr.save(r);
    }

    @Override
    public Reservation retrieveReservation(Integer idReservation) {
        return Rr.findById(idReservation).orElse(null);
    }

    @Override
    public void removeReservation(Integer idReservation) {
        Rr.deleteById(idReservation);
    }

    @Override
    public void assignCenterToReservation(Integer idcenter, Integer idReservation) {
        int i;

        Center C =  Cr.findById(idcenter).orElse(null);
        Reservation R = Rr.findById(idReservation).orElse(null);
        if (C.getNumber()>0) {
            R.setCenter(C);
            Rr.save(R);
            for (int j=0 ; j<R.getNbReservationCenter() ;j++){
                i = C.getNumber() - 1;

            C.setNumber(i); }
  Cr.save(C);

        }
    }

    @Override
    public void ReservationPrice(Integer idcenter, Integer idReservation) {
        float p=0,p1 = 0,p2 =0;
        Center C =  Cr.findById(idcenter).orElse(null);
        Reservation R = Rr.findById(idReservation).orElse(null);
        if (R.getNbReservationCenter()!=0){
            p1=  (C.getPrice()*R.getNbReservationCenter());
            System.out.println("**********"+ p1);
        }
        if (R.getNbReservationActivity()!=0){
            System.out.println(Ar.ActivityPrice(idcenter));
            System.out.println(R.getNbReservationActivity());

            p2= Ar.ActivityPrice(idcenter)*R.getNbReservationActivity();
        }
        p=p1+p2;
      R.setPrice(p);
      Rr.save(R);

    }

}
