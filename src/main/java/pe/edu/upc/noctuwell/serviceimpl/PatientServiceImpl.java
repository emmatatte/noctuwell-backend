package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.PatientPostDTO;
import pe.edu.upc.noctuwell.dtos.SpecialistPostDTO;
import pe.edu.upc.noctuwell.entities.Patient;
import pe.edu.upc.noctuwell.entities.Specialist;
import pe.edu.upc.noctuwell.entities.User;
import pe.edu.upc.noctuwell.repositories.PatientRepository;
import pe.edu.upc.noctuwell.services.PatientService;
import pe.edu.upc.noctuwell.services.UserService;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public UserService userService;

    @Override
    public List<Patient> listAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public PatientPostDTO add(PatientPostDTO patientpostDTO) {
        User userFound = userService.findById(patientpostDTO.getUserId());
        if (userFound == null) {
            System.out.println("ID de usuario no encontrado");
            return null;
        }
        if (patientpostDTO.getFirstName() == null || patientpostDTO.getFirstName().isBlank()) {
            System.out.println("Ingrese el nombre del paciente");
            return null;
        }
        if (patientpostDTO.getPhone() == null || patientpostDTO.getPhone().isBlank()) {
            System.out.println("Ingrese el numero de telefono del paciente");
            return null;
        }
        Patient patient = new Patient(null,
                patientpostDTO.getFirstName(),
                patientpostDTO.getLastName(),
                patientpostDTO.getGender(),
                patientpostDTO.getWeight(),
                patientpostDTO.getHeight(),
                patientpostDTO.getPhone(),
                patientpostDTO.getBirthDate(),
                userFound,
                null,
                null,
                null);
        patient=patientRepository.save(patient);

        patientpostDTO.setId(patient.getId());
        return patientpostDTO;
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
