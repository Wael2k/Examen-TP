package examen_TP.demo.dao;

import examen_TP.demo.dao.model.Message;
import examen_TP.demo.enumaration.MessageTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE " +
            "(:keyword IS NULL OR m.contenu LIKE CONCAT('%', :keyword, '%')) AND " +
            "(:messageType IS NULL OR m.type = :messageType) AND " +
            "(:emetteurId IS NULL OR m.emetteur.id = :emetteurId)")
    List<Message> filterByKeyWord(@Param("keyword") String keyword,
                                            @Param("messageType") MessageTypeEnum messageType,
                                            @Param("emetteurId") Integer emetteurId);

    @Query(value = "SELECT * FROM table_message LIMIT :limit", nativeQuery = true)
    List<Message> getMessagesByLimit(@Param("limit") int limit);


}
