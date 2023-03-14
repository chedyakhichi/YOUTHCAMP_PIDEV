package tn.esprit.integration1.Interfaces;



import tn.esprit.integration1.Entities.TransportTicket;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ITransportTicketService {
    public TransportTicket AddAndAssignTransportTicketTotransport(TransportTicket f, Integer idTransport);
    public TransportTicket updateTransportTicket(TransportTicket f);
    public List<TransportTicket> retrieveAllTransportTickets();
    public TransportTicket addTransportTicket(TransportTicket f) ;
    public void AssignTransportTicketTotransport(Integer IdTicket,Integer idTransport);

        public  void removeTransportTicket(Integer IdTicket);
    public void assignTransportTicketToUser(Integer IdTicket,Integer IdUser);

  //  public void assignTransportTicketToTransport(Integer IdTicket,Integer idTransport);
    public double latitude(String city);
    public double longitude(String city);
    public double calculateDistanceCity(String dep,String des);
    public double calculatePrice(String dep,String des);
    public TransportTicket retreviveTranportTicket(Integer  IdTicket);
    public TransportTicket retreviveTranportTicket2(Integer  IdTicket, HttpServletResponse response);


}
