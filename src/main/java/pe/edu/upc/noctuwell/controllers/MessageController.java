package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.MessageDTO;
import pe.edu.upc.noctuwell.entities.Message;
import pe.edu.upc.noctuwell.services.MessageService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/noctuwell")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> listAll() {
        return new ResponseEntity<>(messageService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(messageService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<MessageDTO> add(@RequestBody MessageDTO messageDTO) {
        messageDTO = messageService.add(messageDTO);
        return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
    }

    @PutMapping("/messages")
    public ResponseEntity<Message> edit(@RequestBody Message message) {
        Message edited = messageService.edit(message);
        return new ResponseEntity<>(edited, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}