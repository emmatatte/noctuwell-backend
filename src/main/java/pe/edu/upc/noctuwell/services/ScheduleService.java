package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.ScheduleDTO;
import pe.edu.upc.noctuwell.entities.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> listAll();
    Schedule findById(Long id);
    ScheduleDTO add(ScheduleDTO scheduleDTO);
    void delete(Long id);
}
