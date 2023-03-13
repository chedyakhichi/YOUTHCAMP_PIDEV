package tn.esprit.integration1.Entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idMess;
    private String content;
    private String sender;
    public String getcontent(){return content;}
    public ChatMessage(String content){this.content = content;}

    @Enumerated(EnumType.STRING)
    private MessageType messageType;



}
