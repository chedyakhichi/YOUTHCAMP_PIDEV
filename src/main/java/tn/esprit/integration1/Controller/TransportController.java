package tn.esprit.integration1.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Transport;
import tn.esprit.integration1.Interfaces.ITransportService;

import java.util.List;
@RestController
@Slf4j

@RequestMapping("/Transport")
public class TransportController {
    @Autowired
    ITransportService transportService;

    @PostMapping("/AddTransport")
    public Transport addTransport(@RequestBody Transport F) {
        Transport transport = transportService.addTransport(F);
        return transport;
    }


    @PutMapping("/update-transport")
    public Transport updateTransport(@RequestBody Transport F) {
        Transport x = transportService.updateTransport(F);
        return x;
    }

    @DeleteMapping("/remove-transport/{transport-id}")
    public void removeTransport(@PathVariable("transport-id") Integer idTransport) {

        transportService.removeTransport(idTransport);
    }

    @PutMapping("/assign-Transport/{idTransport}/{IdUser}")
    @ResponseBody
    public void assignTransportToUser(@PathVariable("idTransport") Integer idTransport, @PathVariable("IdUser") Integer IdUser) {
        transportService.assignTransportToUser(idTransport, IdUser);

    }

    @GetMapping("/retrieve-all-transport")
    public List<Transport> getTransports() {

        List<Transport> listTransports = transportService.findtransports();
        log.info(listTransports.toString());
        return listTransports;
    }

    @GetMapping("/retrieve-transports-by-city")

    public List<Transport> getTransportsbycities(@RequestParam String departure, @RequestParam String destination) {
        List<Transport> list = transportService.findByDepartureAndDestination(departure,destination);
        log.info(list.toString());

        return list;
    }
}