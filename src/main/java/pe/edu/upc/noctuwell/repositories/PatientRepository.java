package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.noctuwell.entities.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(
            value = """
            SELECT p.*
            FROM patients p
            JOIN diagnoses d ON d.history_id IN (
                SELECT h.id FROM histories h WHERE h.patient_id = p.id
            )
            GROUP BY p.id
            ORDER BY COUNT(d.id) DESC
            LIMIT :limit
            """,
            nativeQuery = true
    )
    List<Patient> findTopPatientsByDiagnoses(@Param("limit") int limit);
}

