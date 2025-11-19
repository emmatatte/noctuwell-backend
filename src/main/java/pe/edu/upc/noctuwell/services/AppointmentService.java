package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.AppointmentDTO;
import pe.edu.upc.noctuwell.dtos.AppointmentReportDTO;
import pe.edu.upc.noctuwell.entities.Appointment;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO add(AppointmentDTO appointmentDTO);

    Appointment edit(Appointment appointment);

    void delete(Long id);

    Appointment findById(Long id);

    List<Appointment> listAll();

    List<Appointment> listByPatientId(Long patientId);

    List<Appointment> listBySpecialistId(Long specialistId);

    // Reportes HU066 y HU069
    List<Appointment> findByStatus(String status);
    List<AppointmentReportDTO> reportEspecialistasConMasPacientes();
    List<AppointmentReportDTO> reportPacientesConMasCitas();
}
