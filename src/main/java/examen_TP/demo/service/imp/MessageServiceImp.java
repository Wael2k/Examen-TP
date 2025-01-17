package examen_TP.demo.service.imp;

import examen_TP.demo.config.security.exceptions.DataNotFoundException;
import examen_TP.demo.dao.MessageRepository;
import examen_TP.demo.dao.UserRepository;
import examen_TP.demo.dao.dto.messageDto.MessageCreateRequestDto;
import examen_TP.demo.dao.dto.messageDto.MessageResponseDto;
import examen_TP.demo.dao.dto.messageDto.MessageUpdateRequestDto;
import examen_TP.demo.dao.dto.userDto.UserResponseDto;
import examen_TP.demo.dao.model.Message;
import examen_TP.demo.dao.model.User;
import examen_TP.demo.enumaration.MessageTypeEnum;
import examen_TP.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public MessageResponseDto create(MessageCreateRequestDto messageCreateRequestDto) {
        Message message = new Message();
        message.setContenu(messageCreateRequestDto.getContenu());
        message.setType(messageCreateRequestDto.getType());
        List<Integer> usersAvailable = new ArrayList<>();
        messageCreateRequestDto.getRecepteur().forEach(item -> {
            if(userRepository.findById(item).isPresent()){
                usersAvailable.add(item);
            }else {
                throw new RuntimeException("user not found ");
            }
        });
        message.setRecepteur(usersAvailable);
        User user = userRepository.findById(messageCreateRequestDto.getEmetteur()) .orElseThrow(() -> new DataNotFoundException("NOT_FOUND","User not found"));
        message.setEmetteur(user);
        message = messageRepository.save(message);
        List<Message> messages = user.getMessages();
        messages.add(message);
        user.setMessages(messages);
        userRepository.save(user);
        MessageResponseDto responseDto = MessageResponseDto.builder()
                .id(message.getId())
                .contenu(message.getContenu())
                .type(message.getType())
                .recepteur(message.getRecepteur())
                .emetteur(UserResponseDto.builder()
                        .userName(message.getEmetteur().getUsername())
                        .id(message.getEmetteur().getId())
                        .build())
                .build();
        return responseDto;
    }

    @Override
    public MessageResponseDto update(MessageUpdateRequestDto messageUpdateRequestDto) {
        Message message = messageRepository.findById(messageUpdateRequestDto.getId()) .orElseThrow(() -> new DataNotFoundException("NOT_FOUND","Message not found"));
        message.setContenu(messageUpdateRequestDto.getContenu());
        message.setType(messageUpdateRequestDto.getType());
        List<Integer> usersAvailable = new ArrayList<>();
        message.getRecepteur().forEach(item -> {
            if(userRepository.findById(item).isPresent()){
                usersAvailable.add(item);
            }else {
                throw new RuntimeException("user not found ");
            }
        });
        message.setRecepteur(usersAvailable);
        message = messageRepository.save(message);
        MessageResponseDto responseDto = MessageResponseDto.builder()
                .id(message.getId())
                .contenu(message.getContenu())
                .type(message.getType())
                .recepteur(message.getRecepteur())
                .emetteur(UserResponseDto.builder()
                        .userName(message.getEmetteur().getUsername())
                        .id(message.getEmetteur().getId())
                        .build())
                .build();
        return responseDto;
    }

    @Override
    public List<MessageResponseDto> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(message -> MessageResponseDto.builder()
                        .id(message.getId())
                        .contenu(message.getContenu())
                        .type(message.getType())
                        .recepteur(message.getRecepteur())
                        .emetteur(UserResponseDto.builder()
                                .userName(message.getEmetteur().getUsername())
                                .id(message.getEmetteur().getId())
                                .build())
                        .build())
                .toList();
    }


    @Override
    public void deleteMessage(int messageId) {
        Message message = messageRepository.findById(messageId) .orElseThrow(() -> new RuntimeException("Message not found"));
        messageRepository.delete(message);
    }

    @Override
    public List<MessageResponseDto> filterByKeyWord(String keyword, MessageTypeEnum messageType, Integer emetteurId) {
        return messageRepository.filterByKeyWord(keyword,messageType,emetteurId).stream().map(message -> MessageResponseDto.builder()
                        .id(message.getId())
                        .contenu(message.getContenu())
                        .type(message.getType())
                        .recepteur(message.getRecepteur())
                        .emetteur(UserResponseDto.builder()
                                .userName(message.getEmetteur().getUsername())
                                .id(message.getEmetteur().getId())
                                .build())
                        .build())
                .toList();
    }

    @Override
    public List<MessageResponseDto> getAllMessagesBylIMIT(int limit) {
        return messageRepository.getMessagesByLimit(limit).stream().map(message -> MessageResponseDto.builder()
                        .id(message.getId())
                        .contenu(message.getContenu())
                        .type(message.getType())
                        .recepteur(message.getRecepteur())
                        .emetteur(UserResponseDto.builder()
                                .userName(message.getEmetteur().getUsername())
                                .id(message.getEmetteur().getId())
                                .build())
                        .build())
                .toList();
    }
}
