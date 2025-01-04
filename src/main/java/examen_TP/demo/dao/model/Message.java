package examen_TP.demo.dao.model;

import examen_TP.demo.enumaration.MessageTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "TABLE_MESSAGE")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MessageTypeEnum type;
    private MessageTypeEnum contenu;
    private Long emetteur ;
    @ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
    private List<Long> recepteur;


}
