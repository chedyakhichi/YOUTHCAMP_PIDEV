package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TourReservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourReservation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idTourReservation")
    private Integer idTourReservation; // Clé primaire
    private String location;
    private String guide;
    private Integer nbareserver;
    //@Transient
   // @Temporal( TemporalType.DATE)
   private Integer durationR;
   // relation tourreservation et tour
   @JsonIgnore
   @ManyToOne
    private Tour tour;
    // relation tourreservation et user
    @JsonIgnore
    @ManyToOne
    private User user;



}
