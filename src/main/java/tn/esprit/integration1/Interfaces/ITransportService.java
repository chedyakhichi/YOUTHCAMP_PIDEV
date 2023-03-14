package tn.esprit.integration1.Interfaces;


import tn.esprit.integration1.Entities.Transport;

import java.util.List;

public interface ITransportService {
    public Transport addTransport(Transport f);
    public Transport updateTransport(Transport f);
    public List<Transport> findtransports();
    public List<Transport> findByDepartureAndDestination(String departure, String destination) ;
    public  void removeTransport(Integer idTransport);
    public void assignTransportToUser(Integer idTransport,Integer IdUser);

  /*  public double latitude(String city);
    public double longitude(String city);
    public double calculateDistanceCity(String dep,String des);
    public double calculatePrice(String dep,String des);*/







}
