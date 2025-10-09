package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.dtos.PacientesDTO;

import java.util.List;

public interface PacientesService {
    PacientesDTO add(PacientesDTO dto);
    PacientesDTO findById(Long id);
    List<PacientesDTO> listAll();
    PacientesDTO edit(PacientesDTO dto);
    void delete(Long id);
}
