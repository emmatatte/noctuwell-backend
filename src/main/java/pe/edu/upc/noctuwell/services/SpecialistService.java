package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.SpecialistPostDTO;
import pe.edu.upc.noctuwell.entities.Specialist;
import java.util.List;

public interface SpecialistService {
    List<Specialist> listAll();
    Specialist findById(Long id);
    SpecialistPostDTO add(SpecialistPostDTO specialistpostDTO);
    void delete(Long id);
    List<Specialist> getTopRatedSpecialists(int limit);
    Object getTypeSpecialistWithMostExperience();
}
