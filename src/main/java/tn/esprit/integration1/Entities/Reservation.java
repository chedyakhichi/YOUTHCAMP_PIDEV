package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idReservation")
    private Integer idReservation; // Clé primaire
    private Integer nbReservationCenter;
    private Integer nbReservationActivity;
    private Float Price;
    private LocalDate date;
    @JsonIgnore
    @ManyToOne
    Center center;


}
