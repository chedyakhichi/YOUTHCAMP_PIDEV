package tn.esprit.integration1.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Transport;
import tn.esprit.integration1.Entities.User;
import tn.esprit.integration1.Interfaces.ITransportService;
import tn.esprit.integration1.Repositories.TransportRepository;
import tn.esprit.integration1.Repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class TransportService implements ITransportService {
    @Autowired
    TransportRepository transportRepository;
    UserRepository userRepository;

    public Transport addTransport(Transport f) {
        return transportRepository.save(f);
    }


    public Transport updateTransport(Transport f) {
        return transportRepository.save(f);
    }



    public List<Transport> findtransports() {

        return transportRepository.findAll();
    }
    public void removeTransport(Integer idTransport) {
        transportRepository.deleteById(idTransport);

    }
  /*  @Transactional
    public void assignTransportToUser(Integer idTransport,Integer IdUser) {

            User U = userRepository.findById(IdUser).orElse(null);
            Transport T = transportRepository.findById(idTransport).orElse(null);
        if(T.getNombrePlaces()>0) {
            U.getTransports().add(T);
            transportRepository.save(T);
            T.setNombrePlaces(T.getNombrePlaces() - 1);
        }
        else {
            log.info("hhhhhhhhhhhhhhhhhhhhh");
        }
    }*/
  @Transactional
  public void assignTransportToUser(Integer idTransport,Integer IdUser){
      User U = userRepository.findById(IdUser).orElse(null);
      Transport T = transportRepository.findById(idTransport).orElse(null);
      log.info(T.toString());
      T.setUser(U);
      transportRepository.save(T);



  }

    public List<Transport> findByDepartureAndDestination(String departure, String destination) {
        List<Transport> transports = transportRepository.findAll();
        List<Transport> matchingTransports = new ArrayList<>();

        for (Transport transport : transports) {
            if (transport.getDeparture().equals(departure) && transport.getDestination().equals(destination)) {
                matchingTransports.add(transport);
            }
        }

        return matchingTransports;
    }
}
