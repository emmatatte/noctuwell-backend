package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.DiagnosisDTO;
import pe.edu.upc.noctuwell.entities.Appointment;
import pe.edu.upc.noctuwell.entities.Diagnosis;
import pe.edu.upc.noctuwell.entities.History;
import pe.edu.upc.noctuwell.entities.Specialist;
import pe.edu.upc.noctuwell.repositories.DiagnosisRepository;
import pe.edu.upc.noctuwell.services.AppointmentService;
import pe.edu.upc.noctuwell.services.DiagnosisService;
import pe.edu.upc.noctuwell.services.HistoryService;
import pe.edu.upc.noctuwell.services.SpecialistService;

import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SpecialistService specialistService;

    @Autowired
    private HistoryService historyService;

    @Override
    public DiagnosisDTO add(DiagnosisDTO diagnosisDTO) {
        Appointment appointmentFound = appointmentService.findById(diagnosisDTO.getAppointmentId());
        Specialist specialistFound = specialistService.findById(diagnosisDTO.getSpecialistId());
        History historyFound = historyService.findById(diagnosisDTO.getHistoryId());

        if (appointmentFound == null || specialistFound == null || historyFound == null) {
            return null;
        }

        Diagnosis diagnosis = new Diagnosis(
                null,
                diagnosisDTO.getDescription(),
                diagnosisDTO.getType(),
                diagnosisDTO.getRecommendations(),
                diagnosisDTO.getDate(),
                appointmentFound,
                historyFound,
                specialistFound
        );

        diagnosis = diagnosisRepository.save(diagnosis);
        diagnosisDTO.setId(diagnosis.getId());
        return diagnosisDTO;
    }

    @Override
    public Diagnosis edit(Diagnosis diagnosis) {
        Diagnosis found = findById(diagnosis.getId());
        if (found == null) return null;

        if (diagnosis.getDescription() != null)
            found.setDescription(diagnosis.getDescription());
        if (diagnosis.getType() != null)
            found.setType(diagnosis.getType());
        if (diagnosis.getRecommendations() != null)
            found.setRecommendations(diagnosis.getRecommendations());
        if (diagnosis.getDate() != null)
            found.setDate(diagnosis.getDate());

        return diagnosisRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        Diagnosis found = findById(id);
        if (found != null)
            diagnosisRepository.deleteById(id);
    }

    @Override
    public Diagnosis findById(Long id) {
        return diagnosisRepository.findById(id).orElse(null);
    }

    @Override
    public List<Diagnosis> listAll() {
        return diagnosisRepository.findAll();
    }
}
