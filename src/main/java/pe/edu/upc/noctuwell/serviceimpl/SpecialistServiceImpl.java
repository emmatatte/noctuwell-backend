package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.SpecialistPostDTO;
import pe.edu.upc.noctuwell.entities.Specialist;
import pe.edu.upc.noctuwell.entities.User;
import pe.edu.upc.noctuwell.repositories.SpecialistRepository;
import pe.edu.upc.noctuwell.services.SpecialistService;
import pe.edu.upc.noctuwell.services.UserService;

import java.util.List;

@Service
public class SpecialistServiceImpl implements SpecialistService {

    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired
    public UserService userService;

    @Override
    public List<Specialist> listAll() {
        return specialistRepository.findAll();
    }

    @Override
    public Specialist findById(Long id) {
        return specialistRepository.findById(id).orElse(null);
    }

    @Override
    public SpecialistPostDTO add(SpecialistPostDTO specialistpostDTO) {
        User userFound = userService.findById(specialistpostDTO.getUserId());
        if (userFound == null) {
            System.out.println("ID de usuario no encontrado");
            return null;
        }
        if (specialistpostDTO.getFirstName() == null || specialistpostDTO.getFirstName().isBlank()) {
            return null;
        }
        Specialist specialist = new Specialist(null,
                specialistpostDTO.getFirstName(),
                specialistpostDTO.getLastName(),
                specialistpostDTO.getPhone(),
                specialistpostDTO.getCertification(),
                specialistpostDTO.getDescription(),
                specialistpostDTO.getExperience(),
                userFound,
                null,
                null,
                null);
        specialist=specialistRepository.save(specialist);

        specialistpostDTO.setId(specialist.getId());
        return specialistpostDTO;
    }

    @Override
    public void delete(Long id) {
        specialistRepository.deleteById(id);
    }
}
