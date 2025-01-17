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
    private Integer id;
    @Enumerated(EnumType.STRING)
    private MessageTypeEnum type;
    private String contenu;
    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    private List<Integer> recepteur;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private User emetteur;

}
