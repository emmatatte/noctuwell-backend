package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.MessageDTO;
import pe.edu.upc.noctuwell.entities.Message;

import java.util.List;

public interface MessageService {
    MessageDTO add(MessageDTO messageDTO);
    Message edit(Message message);
    void delete(Long id);
    Message findById(Long id);
    List<Message> listAll();
}
