package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.AppointmentDTO;
import pe.edu.upc.noctuwell.dtos.AppointmentReportDTO;
import pe.edu.upc.noctuwell.entities.Appointment;
import pe.edu.upc.noctuwell.services.AppointmentService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/noctuwell")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> listAll() {
        return new ResponseEntity<>(appointmentService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(appointmentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/appointments/patient/{id}")
    public ResponseEntity<List<Appointment>> listByPatientId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(appointmentService.listByPatientId(id), HttpStatus.OK);
    }

    @GetMapping("/appointments/specialist/{id}")
    public ResponseEntity<List<Appointment>> listBySpecialistId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(appointmentService.listBySpecialistId(id), HttpStatus.OK);
    }

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentDTO> add(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentDTO = appointmentService.add(appointmentDTO);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/appointments")
    public ResponseEntity<Appointment> edit(@RequestBody Appointment appointment) {
        Appointment edited = appointmentService.edit(appointment);
        return new ResponseEntity<>(edited, HttpStatus.OK);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/appointments/status/{status}")
    public ResponseEntity<List<Appointment>> findByStatus(@PathVariable("status") String status) {
        return new ResponseEntity<>(appointmentService.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/appointments/specialists-most-patients")
    public ResponseEntity<List<AppointmentReportDTO>> specialistsMostPatients() {
        List<AppointmentReportDTO> list = appointmentService.reportEspecialistasConMasPacientes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/appointments/patients-most-appointments")
    public ResponseEntity<List<AppointmentReportDTO>> getPatientsWithMostAppointments() {
        List<AppointmentReportDTO> list = appointmentService.reportPacientesConMasCitas();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
