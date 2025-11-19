package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.PatientPostDTO;
import pe.edu.upc.noctuwell.entities.Patient;
import java.util.List;

public interface PatientService {
    List<Patient> listAll();
    Patient findById(Long id);
    PatientPostDTO add(PatientPostDTO patientpostDTO);
    void delete(Long id);
    List<Patient> getTopPatientsByDiagnoses(int limit);
}
