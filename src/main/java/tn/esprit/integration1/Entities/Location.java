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
@Table(name = "Location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idLocation")
    private Integer idLocation; // Clé primaire
    private String description;
    private String place;
    @Enumerated(EnumType.STRING)
   private LocationType locationType ;
    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "location")
    @JsonIgnore
    private Set<Center> centers;







}
