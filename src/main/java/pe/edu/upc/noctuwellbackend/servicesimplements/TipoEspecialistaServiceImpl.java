package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.dtos.TipoEspecialistaDTO;
import pe.edu.upc.noctuwellbackend.entities.TipoEspecialista;
import pe.edu.upc.noctuwellbackend.repositories.TipoEspecialistaRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.TipoEspecialistaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoEspecialistaServiceImpl implements TipoEspecialistaService {

    @Autowired
    private TipoEspecialistaRepository tipoEspecialistaRepository;

    private TipoEspecialistaDTO convertToDTO(TipoEspecialista entity) {
        return new TipoEspecialistaDTO(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }

    private TipoEspecialista convertToEntity(TipoEspecialistaDTO dto) {
        return new TipoEspecialista(dto.getId(), dto.getNombre(), dto.getDescripcion());
    }

    @Override
    public TipoEspecialistaDTO add(TipoEspecialistaDTO dto) {
        TipoEspecialista tipoEspecialista = convertToEntity(dto);
        TipoEspecialista saved = tipoEspecialistaRepository.save(tipoEspecialista);
        return convertToDTO(saved);
    }

    @Override
    public TipoEspecialistaDTO findById(Long id) {
        TipoEspecialista tipoEspecialista = tipoEspecialistaRepository.findById(id).orElse(null);
        if (tipoEspecialista == null) {
            return null;
        }
        return convertToDTO(tipoEspecialista);
    }

    @Override
    public List<TipoEspecialistaDTO> listAll() {
        return tipoEspecialistaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TipoEspecialistaDTO edit(TipoEspecialistaDTO dto) {
        TipoEspecialista existing = tipoEspecialistaRepository.findById(dto.getId()).orElse(null);
        if (existing == null) {
            return null;
        }
        if (dto.getNombre() != null) existing.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) existing.setDescripcion(dto.getDescripcion());
        TipoEspecialista updated = tipoEspecialistaRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (tipoEspecialistaRepository.existsById(id)) {
            tipoEspecialistaRepository.deleteById(id);
        }
    }
}
