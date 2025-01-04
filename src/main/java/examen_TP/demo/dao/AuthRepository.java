package examen_TP.demo.dao;

import examen_TP.demo.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User,Long> {
}
