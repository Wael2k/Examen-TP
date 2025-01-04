package examen_TP.demo.dao;

import examen_TP.demo.dao.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
