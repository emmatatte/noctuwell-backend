package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.TypeSpecialistDTO;
import pe.edu.upc.noctuwell.entities.Plan;
import pe.edu.upc.noctuwell.entities.TypeSpecialist;

import java.util.List;

public interface TypeSpecialistService {
    List<TypeSpecialist> listAll();
    TypeSpecialist findById(Long id);
    TypeSpecialistDTO add(TypeSpecialistDTO typeSpecialistDTO);
    TypeSpecialist edit(TypeSpecialist typeSpecialist);
    void delete(Long id);
}
