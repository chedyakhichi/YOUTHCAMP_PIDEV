package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EvaluationLiv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationLiv implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idEv")
    private Integer idEval; // Clé primaire
    String text ;
    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;

    @JsonIgnore
    @ManyToOne
    private Delivery delivery ;
    @JsonIgnore
    @OneToOne
    private User user  ;




}
