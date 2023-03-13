package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
@EqualsAndHashCode

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="iduser")
    private Integer iduser; // Clé primaire
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private  Role role;
    private String adress_livraison ;
    private Integer poidLivreurmax ;
    private Boolean etat_disponibilité =true ;
    private float PointBonus ;
    @NotNull
    @Column(name = "loyalty_pts")
    private Float loyaltyPts = 0.0f;
    @NotNull
    @Column(name = "discount")
    private Float discount = 0.0f;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate loyaltyPointsExpireDate;


    /////////chedya_Forum////
    //Relation ManyToMany user-Publication
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Publication> publications;

    //Relation OneToMany user-MotIterdit-
    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL)
    private Set<MotInterdit> motInterdits;
//    //////////Dali feedBack /////////////
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore private Set<Feedback> feedbacks;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    private Transport transport;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "userTicket")
    @JsonIgnore
    private Set<TransportTicket> transportTickets;
    /////////khouloud Centre//////
    @ManyToMany
    @JsonIgnore
    private Set<Center> centers;
    @OneToMany( cascade = CascadeType.ALL )
    @JsonIgnore
    private Set<Reservation> reservations;

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "user")
    @JsonIgnore
    private Set<ReviewC> reviews ;
    ////////Nawres livraison ///////
    //relation tour et user
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<Tour> tours;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set <Delivery> deliveries;
    ////////////////othman Boutique e-commerce/////
    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    private List<Command> commands;

}

