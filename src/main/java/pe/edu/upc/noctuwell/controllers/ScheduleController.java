package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.ScheduleDTO;
import pe.edu.upc.noctuwell.entities.Schedule;
import pe.edu.upc.noctuwell.services.ScheduleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/noctuwell/schedules")
@CrossOrigin("*")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> listAll() {
        List<ScheduleDTO> schedules = scheduleService.listAll().stream()
                .map( s -> new ScheduleDTO(
                        s.getId(),
                        s.getDay(),
                        s.getHoraInicio(),
                        s.getHoraFin(),
                        s.getSpecialist().getId()
                )).collect(Collectors.toList());
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> findById(@PathVariable Long id) {
        Schedule schedule = scheduleService.findById(id);
        return (schedule != null)
                ? new ResponseEntity<>(schedule, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> add(@RequestBody ScheduleDTO scheduleDTO) {
        scheduleDTO = scheduleService.add(scheduleDTO);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
