package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.MessageDTO;
import pe.edu.upc.noctuwell.entities.*;
import pe.edu.upc.noctuwell.repositories.MessageRepository;
import pe.edu.upc.noctuwell.services.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private SpecialistService specialistService;

    @Override
    public MessageDTO add(MessageDTO messageDTO) {
        Appointment appointment = appointmentService.findById(messageDTO.getAppointmentId());
        Patient patient = patientService.findById(messageDTO.getPatientId());
        Specialist specialist = specialistService.findById(messageDTO.getSpecialistId());

        if (appointment == null || patient == null || specialist == null) {
            return null;
        }

        Message message = new Message(
                null,
                messageDTO.getContent(),
                messageDTO.getSentAt() != null ? messageDTO.getSentAt() : LocalDateTime.now(),
                appointment,
                patient,
                specialist
        );

        message = messageRepository.save(message);
        messageDTO.setId(message.getId());
        return messageDTO;
    }

    @Override
    public Message edit(Message message) {
        Message found = findById(message.getId());
        if (found == null) return null;

        if (message.getContent() != null)
            found.setContent(message.getContent());

        return messageRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        Message found = findById(id);
        if (found != null)
            messageRepository.deleteById(id);
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> listAll() {
        return messageRepository.findAll();
    }
}
