package examen_TP.demo.service;


import examen_TP.demo.dao.dto.messageDto.MessageCreateRequestDto;
import examen_TP.demo.dao.dto.messageDto.MessageResponseDto;
import examen_TP.demo.dao.dto.messageDto.MessageUpdateRequestDto;
import examen_TP.demo.enumaration.MessageTypeEnum;

import java.awt.*;
import java.util.List;

public interface MessageService  {
MessageResponseDto create (MessageCreateRequestDto messageCreateRequestDto);
MessageResponseDto update (MessageUpdateRequestDto messageUpdateRequestDto);
List<MessageResponseDto> getAllMessages ();
void deleteMessage (int messageId);
List<MessageResponseDto> filterByKeyWord (String keyword, MessageTypeEnum messageType, Integer emetteurId);
    List<MessageResponseDto> getAllMessagesBylIMIT (int limit);


}
