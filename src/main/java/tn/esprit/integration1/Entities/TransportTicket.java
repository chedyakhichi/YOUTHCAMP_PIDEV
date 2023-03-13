package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class TransportTicket implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
       @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer idTransportTicket;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double distance;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double prix;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Transport transport;
    @ManyToOne
    @JsonIgnore
    User userTicket;



}
