package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.dtos.ReseñaDTO;
import pe.edu.upc.noctuwellbackend.entities.Especialistas;
import pe.edu.upc.noctuwellbackend.entities.Pacientes;
import pe.edu.upc.noctuwellbackend.entities.Reseña;
import pe.edu.upc.noctuwellbackend.repositories.EspecialistasRepository;
import pe.edu.upc.noctuwellbackend.repositories.PacientesRepository;
import pe.edu.upc.noctuwellbackend.repositories.ReseñaRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.ReseñaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReseñaServiceImpl implements ReseñaService {

    @Autowired
    private ReseñaRepository reseñaRepository;

    @Autowired
    private PacientesRepository pacientesRepository;

    @Autowired
    private EspecialistasRepository especialistasRepository;

    private Reseña convertToEntity(ReseñaDTO dto) {
        Reseña entity = new Reseña();
        entity.setId(dto.getId());
        entity.setComentario(dto.getComentario());
        entity.setFecha(dto.getFecha());
        entity.setCalificacion(dto.getCalificacion());

        if (dto.getPacienteId() != null) {
            Pacientes p = pacientesRepository.findById(dto.getPacienteId()).orElse(null);
            entity.setPacientes(p);
        }
        if (dto.getEspecialistaId() != null) {
            Especialistas e = especialistasRepository.findById(dto.getEspecialistaId()).orElse(null);
            entity.setEspecialistas(e);
        }
        return entity;
    }

    private ReseñaDTO convertToDTO(Reseña entity) {
        ReseñaDTO dto = new ReseñaDTO();
        dto.setId(entity.getId());
        dto.setComentario(entity.getComentario());
        dto.setFecha(entity.getFecha());
        dto.setCalificacion(entity.getCalificacion());
        dto.setPacienteId(entity.getPacientes() != null ? entity.getPacientes().getId() : null);
        dto.setEspecialistaId(entity.getEspecialistas() != null ? entity.getEspecialistas().getId() : null);
        return dto;
    }

    @Override
    public ReseñaDTO add(ReseñaDTO dto) {
        Reseña entity = convertToEntity(dto);
        Reseña saved = reseñaRepository.save(entity);
        return convertToDTO(saved);
    }

    @Override
    public ReseñaDTO findById(Long id) {
        Reseña entity = reseñaRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return convertToDTO(entity);
    }

    @Override
    public List<ReseñaDTO> listAll() {
        List<Reseña> list = reseñaRepository.findAll();
        return list.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReseñaDTO edit(ReseñaDTO dto) {
        Reseña existing = reseñaRepository.findById(dto.getId()).orElse(null);
        if (existing == null) {
            return null;
        }

        if (dto.getComentario() != null) { existing.setComentario(dto.getComentario()); }
        if (dto.getFecha() != null) { existing.setFecha(dto.getFecha()); }
        if (dto.getCalificacion() != null) { existing.setCalificacion(dto.getCalificacion()); }

        if (dto.getPacienteId() != null) {
            Pacientes p = pacientesRepository.findById(dto.getPacienteId()).orElse(null);
            existing.setPacientes(p);
        }
        if (dto.getEspecialistaId() != null) {
            Especialistas e = especialistasRepository.findById(dto.getEspecialistaId()).orElse(null);
            existing.setEspecialistas(e);
        }

        Reseña updated = reseñaRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (reseñaRepository.existsById(id)) {
            reseñaRepository.deleteById(id);
        }
    }
}
