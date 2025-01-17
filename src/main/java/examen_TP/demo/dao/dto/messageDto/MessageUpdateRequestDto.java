package examen_TP.demo.dao.dto.messageDto;

import examen_TP.demo.enumaration.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageUpdateRequestDto {
    private Integer id;
    private MessageTypeEnum type;
    private String contenu;
    private List<Integer> recepteur;
}
