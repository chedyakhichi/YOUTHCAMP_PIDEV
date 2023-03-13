package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Transport implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idTransport;
    private String Departure;
    private String Destination;


    // @Temporal(TemporalType.DATE)
    private LocalDate DateTransport;
    private Integer NombrePlaces;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

}
