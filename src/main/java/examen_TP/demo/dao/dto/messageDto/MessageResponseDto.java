package examen_TP.demo.dao.dto.messageDto;

import examen_TP.demo.dao.dto.userDto.UserResponseDto;
import examen_TP.demo.enumaration.MessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MessageResponseDto {
    private Integer id;
    private MessageTypeEnum type;
    private String contenu;
    private List<Integer> recepteur;
    private UserResponseDto emetteur;
}
