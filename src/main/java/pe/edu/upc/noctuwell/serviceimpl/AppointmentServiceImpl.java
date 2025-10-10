package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.AppointmentDTO;
import pe.edu.upc.noctuwell.entities.Appointment;
import pe.edu.upc.noctuwell.entities.Patient;
import pe.edu.upc.noctuwell.entities.Specialist;
import pe.edu.upc.noctuwell.repositories.AppointmentRepository;
import pe.edu.upc.noctuwell.services.AppointmentService;
import pe.edu.upc.noctuwell.services.PatientService;
import pe.edu.upc.noctuwell.services.SpecialistService;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    SpecialistService specialistService;

    @Override
    public AppointmentDTO add(AppointmentDTO appointmentDTO) {
        Specialist specialistFound = specialistService.findById(appointmentDTO.getSpecialistId());
        Patient patientFound = patientService.findById(appointmentDTO.getPatientId());

        if (specialistFound == null || patientFound == null) {
            System.out.println("Specialist or Patient not found");
            return null;
        }

        Appointment appointment = new Appointment(null,
                appointmentDTO.getDate(),
                appointmentDTO.getTime(),
                appointmentDTO.getReason(),
                appointmentDTO.getStatus(),
                patientFound,
                specialistFound,
                null,
                null
        );

        appointment = appointmentRepository.save(appointment);
        appointmentDTO.setId(appointment.getId());
        return appointmentDTO;
    }

    @Override
    public Appointment edit(Appointment appointment) {
        Appointment found = findById(appointment.getId());
        if (found == null) return null;

        if (appointment.getDate() != null)
            found.setDate(appointment.getDate());
        if (appointment.getTime() != null)
            found.setTime(appointment.getTime());
        if (appointment.getReason() != null)
            found.setReason(appointment.getReason());
        if (appointment.getStatus() != null)
            found.setStatus(appointment.getStatus());

        return appointmentRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        Appointment found = findById(id);
        if (found != null)
            appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> listAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> listByPatientId(Long patientId) {
        return appointmentRepository.findByPatient_Id(patientId);
    }

    @Override
    public List<Appointment> listBySpecialistId(Long specialistId) {
        return appointmentRepository.findBySpecialist_Id(specialistId);
    }
}
