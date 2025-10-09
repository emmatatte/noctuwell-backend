package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.dtos.HorariosDTO;
import pe.edu.upc.noctuwellbackend.entities.Especialistas;
import pe.edu.upc.noctuwellbackend.entities.Horarios;
import pe.edu.upc.noctuwellbackend.repositories.EspecialistasRepository;
import pe.edu.upc.noctuwellbackend.repositories.HorariosRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.HorariosService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorariosServiceImpl implements HorariosService {

    @Autowired
    private HorariosRepository horariosRepository;

    @Autowired
    private EspecialistasRepository especialistasRepository;

    private Horarios convertToEntity(HorariosDTO dto) {
        Horarios entity = new Horarios();
        entity.setId(dto.getId());
        entity.setDia_semana(dto.getDia_semana());
        entity.setHora_inicio(dto.getHora_inicio());
        entity.setHora_fin(dto.getHora_fin());
        entity.setDisponibilidad(dto.getDisponibilidad());

        if (dto.getEspecialistaId() != null) {
            Especialistas e = especialistasRepository.findById(dto.getEspecialistaId()).orElse(null);
            entity.setEspecialistas(e); // tu campo es "especialistas" (plural)
        }
        return entity;
    }

    private HorariosDTO convertToDTO(Horarios entity) {
        HorariosDTO dto = new HorariosDTO();
        dto.setId(entity.getId());
        dto.setDia_semana(entity.getDia_semana());
        dto.setHora_inicio(entity.getHora_inicio());
        dto.setHora_fin(entity.getHora_fin());
        dto.setDisponibilidad(entity.getDisponibilidad());
        dto.setEspecialistaId(entity.getEspecialistas() != null ? entity.getEspecialistas().getId() : null);
        return dto;
    }

    @Override
    public HorariosDTO add(HorariosDTO dto) {
        Horarios entity = convertToEntity(dto);
        Horarios saved = horariosRepository.save(entity);
        return convertToDTO(saved);
    }

    @Override
    public HorariosDTO findById(Long id) {
        Horarios entity = horariosRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public List<HorariosDTO> listAll() {
        List<Horarios> list = horariosRepository.findAll();
        return list.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HorariosDTO edit(HorariosDTO dto) {
        Horarios existing = horariosRepository.findById(dto.getId()).orElse(null);
        if (existing == null) {
            return null;
        }

        if (dto.getDia_semana() != null) { existing.setDia_semana(dto.getDia_semana()); }
        if (dto.getHora_inicio() != null) { existing.setHora_inicio(dto.getHora_inicio()); }
        if (dto.getHora_fin() != null) { existing.setHora_fin(dto.getHora_fin()); }
        if (dto.getDisponibilidad() != null) { existing.setDisponibilidad(dto.getDisponibilidad()); }

        if (dto.getEspecialistaId() != null) {
            Especialistas e = especialistasRepository.findById(dto.getEspecialistaId()).orElse(null);
            existing.setEspecialistas(e);
        }

        Horarios updated = horariosRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (horariosRepository.existsById(id)) {
            horariosRepository.deleteById(id);
        }
    }
}

