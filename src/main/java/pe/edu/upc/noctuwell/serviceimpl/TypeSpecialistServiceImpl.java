package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.TypeSpecialistDTO;
import pe.edu.upc.noctuwell.entities.Plan;
import pe.edu.upc.noctuwell.entities.TypeSpecialist;
import pe.edu.upc.noctuwell.repositories.TypeSpecialistRepository;
import pe.edu.upc.noctuwell.services.TypeSpecialistService;

import java.util.List;

@Service
public class TypeSpecialistServiceImpl implements TypeSpecialistService {

    @Autowired
    private TypeSpecialistRepository typeSpecialistRepository;

    @Override
    public List<TypeSpecialist> listAll() {
        return typeSpecialistRepository.findAll();
    }

    @Override
    public TypeSpecialist findById(Long id) {
        return typeSpecialistRepository.findById(id).orElse(null);
    }

    @Override
    public TypeSpecialistDTO add(TypeSpecialistDTO typeSpecialistDTO) {
        if (typeSpecialistDTO.getName() == null || typeSpecialistDTO.getName().isBlank()) {
            return null;
        }
        TypeSpecialist typeSpecialist = new TypeSpecialist(null,
                typeSpecialistDTO.getName(),
                typeSpecialistDTO.getDescription(),
                null);
        typeSpecialist = typeSpecialistRepository.save(typeSpecialist);
        typeSpecialistDTO.setId(typeSpecialist.getId());
        return typeSpecialistDTO;
    }

    @Override
    public TypeSpecialist edit(TypeSpecialist typeSpecialist) {
        TypeSpecialist found = findById(typeSpecialist.getId());
        if (found == null) return null;
        if (typeSpecialist.getName() != null)
            found.setName(typeSpecialist.getName());
        if (typeSpecialist.getDescription() != null)
            found.setDescription(typeSpecialist.getDescription());
        return typeSpecialistRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        typeSpecialistRepository.deleteById(id);
    }

}
