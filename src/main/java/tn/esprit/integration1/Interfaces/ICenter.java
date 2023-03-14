package tn.esprit.integration1.Interfaces;

import tn.esprit.integration1.Entities.Center;


import java.util.List;

public interface ICenter {
    List<Center> retrieveAllCenters();

    Center addCenter(Center c);

    Center updateCenter (Center c);

    Center retrieveCenter (Integer idcenter);
    void removeCenter(Integer idcenter);
    public void assignCenterToLocation (Integer idcenter,Integer idLocation ) ;
    public Integer nombreRatingCenter(Integer idcenter);
    public List<Center> getAllCentersSortedByPrice();
    public void statistiques ();
}
