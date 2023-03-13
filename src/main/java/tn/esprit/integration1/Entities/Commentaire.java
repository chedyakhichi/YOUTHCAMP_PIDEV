package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idCom;
    private String commentText;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate commentDate;


    //Publication+commentaire
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Publication Pubreact;
    //Commentaire+user
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;


}
