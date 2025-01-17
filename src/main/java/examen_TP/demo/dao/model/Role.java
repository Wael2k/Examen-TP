package examen_TP.demo.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import examen_TP.demo.enumaration.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "TABLE_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private RoleEnum name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // Prevents serialization of the users field
    private List<User> users;

}
