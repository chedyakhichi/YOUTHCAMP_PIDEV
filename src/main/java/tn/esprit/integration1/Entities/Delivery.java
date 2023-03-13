package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idDelivery")
    private Integer idDelivery; // Clé primaire
    private String adress;
    private  Integer etat ;
    private Integer poids  ;
    private  float prixLivraison ;
    @JsonFormat(pattern = "yyyy-MM-dd")
   private LocalDate deliveryDate;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus ;
    @Enumerated(EnumType.STRING)
   private DeliveryType deliveryType ;
/////////////relation Nawres/////////////
//    relation delivery et user
    @JsonIgnore
    @ManyToOne
    private User user ;

    // RELATION livraison commande
    @JsonIgnore
    @OneToOne(mappedBy = "delivery")
   private Command command ;


}
