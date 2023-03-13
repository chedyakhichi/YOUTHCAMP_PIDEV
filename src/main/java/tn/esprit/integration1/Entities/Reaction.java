package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reaction implements Serializable {

   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Integer idReac;
   //private Integer NbrRecationParUser=0;
   @Enumerated(EnumType.STRING)
   private TypeReaction typeReaction;

   //Publication+reac
   @ManyToOne(cascade = CascadeType.ALL)
   @JsonIgnore
   private Publication Pubreact;
  //reac+user
   @ManyToOne(cascade = CascadeType.ALL)
   @JsonIgnore
   private User user;

}
