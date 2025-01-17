package examen_TP.demo.controller;


import examen_TP.demo.dao.dto.messageDto.MessageCreateRequestDto;
import examen_TP.demo.dao.dto.messageDto.MessageResponseDto;
import examen_TP.demo.dao.dto.messageDto.MessageUpdateRequestDto;
import examen_TP.demo.enumaration.MessageTypeEnum;
import examen_TP.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/message/")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("create")
    public ResponseEntity<MessageResponseDto> create(@RequestBody MessageCreateRequestDto messageCreateRequestDto) {

        return ResponseEntity.ok(messageService.create(messageCreateRequestDto));
    }
    @PutMapping("update")
    public ResponseEntity<MessageResponseDto> update(@RequestBody MessageUpdateRequestDto messageUpdateRequestDto ) {

        return ResponseEntity.ok(messageService.update(messageUpdateRequestDto));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
       messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("getAll")
    public ResponseEntity<List<MessageResponseDto>> getAll(){
        return ResponseEntity.ok(messageService.getAllMessages());
    }
    @GetMapping("filterByKeyWord")
    public ResponseEntity<List<MessageResponseDto>> filterByKeyWord(@RequestParam("keyWord") String keyWord, @RequestParam("messageTypeEnum") MessageTypeEnum messageTypeEnum, @RequestParam("emetteurId") Integer emetteurId){

        return ResponseEntity.ok(messageService.filterByKeyWord(keyWord, messageTypeEnum, emetteurId));
    }
    @GetMapping("getAllMessagesByLimit")
    public ResponseEntity<List<MessageResponseDto>> filterByKeyWord(@RequestParam("limit") int limit){
        return ResponseEntity.ok(messageService.getAllMessagesBylIMIT(limit));
    }
}
