package tn.esprit.integration1.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Entities.Reservation;
import tn.esprit.integration1.Services.ServiceReservation;
import tn.esprit.integration1.Services.WeatherService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Reservation")
public class ReservationRestController {
    ServiceReservation Sr;
    WeatherService Sw;

    @GetMapping("/retrieve-all-Reservations")
    public List<Reservation> getReservations() {
        List<Reservation> listReservations = Sr.retrieveAllReservations();
        return listReservations;
    }

    @GetMapping("/retrieve-Reservation/{Reservation-id}")
    public Reservation retrieveReservation(@PathVariable("Reservation-id") Integer idReservation) {
        return Sr.retrieveReservation(idReservation);
    }

    @PostMapping("/add-Reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        Reservation Reservation = Sr.addReservation(r);
        return Reservation;
    }

    @PutMapping("/update-Reservation")
    public Reservation updateReservation(@RequestBody Reservation r) {
        Reservation Reservation = Sr.updateReservation(r);
        return Reservation;
    }

    @DeleteMapping("/remove-Reservation/{Reservation-id}")
    public void removeOperateur(@PathVariable("Reservation-id") Integer RId) {
        Sr.removeReservation(RId);
    }

    @PutMapping("ReservationCenter/{center-id}/{Reservation-id}")
    public void ReservationCenter(@PathVariable("center-id") Integer idC, @PathVariable("Reservation-id") Integer idR) {
        Sr.assignCenterToReservation(idC, idR);

    }

    @PostMapping("/ReservationPrice/{center-id}/{Reservation-id}")
    public void ReservationPrice(@PathVariable("center-id") Integer idC, @PathVariable("Reservation-id") Integer idR) {
        Sr.ReservationPrice(idC, idR);

    }


}
