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
@Table(name = "Activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idActivity")
    private Integer idActivity; // Clé primaire
    private String location;
    private Float PriceA;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType ;
   private LocalDate startDate;
    private LocalDate endDate;
    @JsonIgnore
    @ManyToOne
    Center center;
}
