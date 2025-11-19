package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.DiagnosisDTO;
import pe.edu.upc.noctuwell.entities.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    DiagnosisDTO add(DiagnosisDTO diagnosisDTO);

    Diagnosis edit(Diagnosis diagnosis);

    void delete(Long id);

    Diagnosis findById(Long id);

    List<Diagnosis> listAll();
}
