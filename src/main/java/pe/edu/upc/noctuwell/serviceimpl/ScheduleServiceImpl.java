package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.ScheduleDTO;
import pe.edu.upc.noctuwell.entities.Schedule;
import pe.edu.upc.noctuwell.entities.Specialist;
import pe.edu.upc.noctuwell.repositories.ScheduleRepository;
import pe.edu.upc.noctuwell.services.ScheduleService;
import pe.edu.upc.noctuwell.services.SpecialistService;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    SpecialistService specialistService;

    @Override
    public List<Schedule> listAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @Override
    public ScheduleDTO add(ScheduleDTO scheduleDTO) {
        Specialist specialist = specialistService.findById(scheduleDTO.getSpecialistId());
        if(specialist == null) {
            System.out.println("ID de Especialista no encontrado");
            return null;
        }
        if(scheduleDTO.getDay() == null || scheduleDTO.getDay().isBlank()) {
            return null;
        }
        Schedule schedule = new Schedule(null,
                scheduleDTO.getDay(),
                scheduleDTO.getHoraInicio(),
                scheduleDTO.getHoraFin(),
                specialist);
        schedule=scheduleRepository.save(schedule);
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
