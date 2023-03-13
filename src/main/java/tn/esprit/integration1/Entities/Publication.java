package tn.esprit.integration1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
//@ToString
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idPub;
    private Integer nbrcomment=0;
    @ToString.Include
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ToString.Include
    private LocalDate pubDate;
    @Enumerated(EnumType.STRING)

    private TypePublication typePublication;
    //Relation ManyToMany user-Publication
    @JsonIgnore
    @ManyToMany(mappedBy = "publications", cascade = CascadeType.ALL)
    private Set<User> users;

    //Relation OneToMany Publication-Reaction
    @JsonIgnore
    @OneToMany
    private Set<Reaction> reactions;


}

