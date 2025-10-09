package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.dtos.PacientesDTO;
import pe.edu.upc.noctuwellbackend.entities.Pacientes;
import pe.edu.upc.noctuwellbackend.entities.Plan;
import pe.edu.upc.noctuwellbackend.entities.User;
import pe.edu.upc.noctuwellbackend.repositories.PacientesRepository;
import pe.edu.upc.noctuwellbackend.repositories.PlanRepository;
import pe.edu.upc.noctuwellbackend.repositories.UserRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.PacientesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacientesServiceImpl implements PacientesService {

    @Autowired
    private PacientesRepository pacientesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    private Pacientes convertToEntity(PacientesDTO dto) {
        Pacientes entity = new Pacientes();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setSexo(dto.getSexo());
        if (dto.getPeso() != null) { entity.setPeso(dto.getPeso()); }
        if (dto.getAltura() != null) { entity.setAltura(dto.getAltura()); }
        entity.setTelefono(dto.getTelefono());

        if (dto.getUserId() != null) {
            User u = userRepository.findById(dto.getUserId()).orElse(null);
            entity.setUser(u);
        }
        if (dto.getPlanId() != null) {
            Plan p = planRepository.findById(dto.getPlanId()).orElse(null);
            entity.setPlan(p);
        }
        return entity;
    }

    private PacientesDTO convertToDTO(Pacientes entity) {
        PacientesDTO dto = new PacientesDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setSexo(entity.getSexo());
        dto.setPeso(entity.getPeso());
        dto.setAltura(entity.getAltura());
        dto.setTelefono(entity.getTelefono());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setPlanId(entity.getPlan() != null ? entity.getPlan().getId() : null);
        return dto;
    }

    @Override
    public PacientesDTO add(PacientesDTO dto) {
        Pacientes entity = convertToEntity(dto);
        Pacientes saved = pacientesRepository.save(entity);
        return convertToDTO(saved);
    }

    @Override
    public PacientesDTO findById(Long id) {
        Pacientes entity = pacientesRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public List<PacientesDTO> listAll() {
        List<Pacientes> list = pacientesRepository.findAll();
        return list.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PacientesDTO edit(PacientesDTO dto) {
        Pacientes existing = pacientesRepository.findById(dto.getId()).orElse(null);
        if (existing == null) {
            return null;
        }

        if (dto.getNombre() != null) { existing.setNombre(dto.getNombre()); }
        if (dto.getApellido() != null) { existing.setApellido(dto.getApellido()); }
        if (dto.getFechaNacimiento() != null) { existing.setFechaNacimiento(dto.getFechaNacimiento()); }
        if (dto.getSexo() != null) { existing.setSexo(dto.getSexo()); }
        if (dto.getPeso() != null) { existing.setPeso(dto.getPeso()); }
        if (dto.getAltura() != null) { existing.setAltura(dto.getAltura()); }
        if (dto.getTelefono() != null) { existing.setTelefono(dto.getTelefono()); }

        if (dto.getUserId() != null) {
            User u = userRepository.findById(dto.getUserId()).orElse(null);
            existing.setUser(u);
        }
        if (dto.getPlanId() != null) {
            Plan p = planRepository.findById(dto.getPlanId()).orElse(null);
            existing.setPlan(p);
        }

        Pacientes updated = pacientesRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (pacientesRepository.existsById(id)) {
            pacientesRepository.deleteById(id);
        }
    }
}
