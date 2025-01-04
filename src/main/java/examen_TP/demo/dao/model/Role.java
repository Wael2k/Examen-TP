package examen_TP.demo.dao.model;

import examen_TP.demo.enumaration.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TABLE_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RoleEnum name;

}
