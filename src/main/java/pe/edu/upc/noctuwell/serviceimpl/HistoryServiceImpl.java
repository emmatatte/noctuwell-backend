package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.HistoryDTO;
import pe.edu.upc.noctuwell.entities.History;
import pe.edu.upc.noctuwell.entities.Patient;
import pe.edu.upc.noctuwell.repositories.HistoryRepository;
import pe.edu.upc.noctuwell.services.HistoryService;
import pe.edu.upc.noctuwell.services.PatientService;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private PatientService patientService;

    @Override
    public HistoryDTO add(HistoryDTO historyDTO) {
        Patient patientFound = patientService.findById(historyDTO.getPatientId());
        if (patientFound == null) {
            return null;
        }

        History history = new History(
                null,
                historyDTO.getBackground(),
                historyDTO.getAllergies(),
                historyDTO.getMedications(),
                patientFound
        );

        history = historyRepository.save(history);
        historyDTO.setId(history.getId());
        return historyDTO;
    }

    @Override
    public History edit(History history) {
        History found = findById(history.getId());
        if (found == null) return null;

        if (history.getBackground() != null)
            found.setBackground(history.getBackground());
        if (history.getAllergies() != null)
            found.setAllergies(history.getAllergies());
        if (history.getMedications() != null)
            found.setMedications(history.getMedications());

        return historyRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        History found = findById(id);
        if (found != null)
            historyRepository.deleteById(id);
    }

    @Override
    public History findById(Long id) {
        return historyRepository.findById(id).orElse(null);
    }

    @Override
    public List<History> listAll() {
        return historyRepository.findAll();
    }
}
