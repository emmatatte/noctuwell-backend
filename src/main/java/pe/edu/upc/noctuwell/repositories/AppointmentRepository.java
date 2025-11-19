package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.noctuwell.entities.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient_Id(Long patientId);
    List<Appointment> findBySpecialist_Id(Long specialistId);

    //HU66 JPQL -- Funciona
    @Query("SELECT a.specialist.id, CONCAT(a.specialist.firstName, ' ', a.specialist.lastName), COUNT(DISTINCT a.patient.id) " +
            "FROM Appointment a " +
            "WHERE a.status = 'COMPLETO' " +
            "GROUP BY a.specialist.id, a.specialist.firstName, a.specialist.lastName " +
            "ORDER BY COUNT(DISTINCT a.patient.id) DESC")
    List<Object[]> reportEspecialistasConMasPacientesJPQL();

    // HU69 SQL -- Funciona para mostrar a los 2 pacientes con mas citas
    @Query(value = "SELECT p.id, CONCAT(p.first_name, ' ', p.last_name) AS patient_name, COUNT(a.id) AS total_appointments " +
            "FROM appointments a " +
            "JOIN patients p ON a.patient_id = p.id " +
            "WHERE a.status = 'COMPLETO' " +
            "GROUP BY p.id, p.first_name, p.last_name " +
            "ORDER BY total_appointments DESC " +
            "LIMIT 2",
            nativeQuery = true)
    List<Object[]> reportPacientesConMasCitasSQL();

    // HU78 Query Method -- Funciona
    List<Appointment> findByStatus(String status);
}
