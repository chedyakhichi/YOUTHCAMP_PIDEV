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
@Table(name = "Center")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Center implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idcenter")

    private Integer idcenter; // Clé primaire
    private String name;
    private Integer number;
    private String state;
    private Float Price;
    private Integer rating;
    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "center")
    @JsonIgnore
    private Set<Activity> activities ;
    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "center")
    @JsonIgnore
    private Set<Reservation> reservations ;
    @ManyToMany( cascade = CascadeType.ALL ,mappedBy = "centers")
    @JsonIgnore
    private Set<User>users;
    @ManyToOne
    @JsonIgnore
    private Location location;
    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "center")
    @JsonIgnore
    private Set<ReviewC> reviews ;




}
