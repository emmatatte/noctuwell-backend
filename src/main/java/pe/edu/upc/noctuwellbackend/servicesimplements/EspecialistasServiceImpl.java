package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.dtos.EspecialistasDTO;
import pe.edu.upc.noctuwellbackend.entities.Especialistas;
import pe.edu.upc.noctuwellbackend.entities.TipoEspecialista;
import pe.edu.upc.noctuwellbackend.entities.User;
import pe.edu.upc.noctuwellbackend.repositories.EspecialistasRepository;
import pe.edu.upc.noctuwellbackend.repositories.TipoEspecialistaRepository;
import pe.edu.upc.noctuwellbackend.repositories.UserRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.EspecialistasService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecialistasServiceImpl implements EspecialistasService {

    @Autowired
    private EspecialistasRepository especialistasRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TipoEspecialistaRepository tipoEspecialistaRepository;

    private Especialistas convertToEntity(EspecialistasDTO dto) {
        Especialistas entity = new Especialistas();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setExperiencia(dto.getExperiencia());
        entity.setCertificaciones(dto.getCertificaciones());
        entity.setDescripcion(dto.getDescripcion());
        entity.setTelefono(dto.getTelefono());

        if (dto.getUserId() != null) {
            User u = userRepository.findById(dto.getUserId()).orElse(null);
            entity.setUser(u);
        }
        if (dto.getTipoEspecialistaId() != null) {
            TipoEspecialista t = tipoEspecialistaRepository.findById(dto.getTipoEspecialistaId()).orElse(null);
            entity.setTipoEspecialista(t);
        }
        return entity;
    }

    private EspecialistasDTO convertToDTO(Especialistas entity) {
        EspecialistasDTO dto = new EspecialistasDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setExperiencia(entity.getExperiencia());
        dto.setCertificaciones(entity.getCertificaciones());
        dto.setDescripcion(entity.getDescripcion());
        dto.setTelefono(entity.getTelefono());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setTipoEspecialistaId(entity.getTipoEspecialista() != null ? entity.getTipoEspecialista().getId() : null);
        return dto;
    }

    @Override
    public EspecialistasDTO add(EspecialistasDTO dto) {
        Especialistas entity = convertToEntity(dto);
        Especialistas saved = especialistasRepository.save(entity);
        return convertToDTO(saved);
    }

    @Override
    public EspecialistasDTO findById(Long id) {
        Especialistas entity = especialistasRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public List<EspecialistasDTO> listAll() {
        List<Especialistas> list = especialistasRepository.findAll();
        return list.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EspecialistasDTO edit(EspecialistasDTO dto) {
        Especialistas existing = especialistasRepository.findById(dto.getId()).orElse(null);
        if (existing == null) {
            return null;
        }

        if (dto.getNombre() != null) { existing.setNombre(dto.getNombre()); }
        if (dto.getApellido() != null) { existing.setApellido(dto.getApellido()); }
        if (dto.getExperiencia() != null) { existing.setExperiencia(dto.getExperiencia()); }
        if (dto.getCertificaciones() != null) { existing.setCertificaciones(dto.getCertificaciones()); }
        if (dto.getDescripcion() != null) { existing.setDescripcion(dto.getDescripcion()); }
        if (dto.getTelefono() != null) { existing.setTelefono(dto.getTelefono()); }

        if (dto.getUserId() != null) {
            User u = userRepository.findById(dto.getUserId()).orElse(null);
            existing.setUser(u);
        }
        if (dto.getTipoEspecialistaId() != null) {
            TipoEspecialista t = tipoEspecialistaRepository.findById(dto.getTipoEspecialistaId()).orElse(null);
            existing.setTipoEspecialista(t);
        }

        Especialistas updated = especialistasRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (especialistasRepository.existsById(id)) {
            especialistasRepository.deleteById(id);
        }
    }
}
