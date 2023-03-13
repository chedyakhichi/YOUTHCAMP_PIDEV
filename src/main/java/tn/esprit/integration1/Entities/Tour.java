package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Tour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idTour")
    private Integer idTour; // Clé primaire
    private String locationName;
    private String description;
    private Integer duration;
    private  Integer nbD;
    //@Transient
    private  Integer nbR=0;

    @JsonIgnore
   //relation tour et user
    @ManyToOne
    private User user ;
    @JsonIgnore
// relation tour reservation et tour
    @OneToMany(cascade = CascadeType.ALL, mappedBy="tour")
    private Set<TourReservation> tourReservations;









}
