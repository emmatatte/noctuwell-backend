package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.dtos.ReseñaDTO;

import java.util.List;

public interface ReseñaService {
    ReseñaDTO add(ReseñaDTO reseñaDTO);
    ReseñaDTO findById(Long id);
    List<ReseñaDTO> listAll();
    ReseñaDTO edit(ReseñaDTO reseñaDTO);
    void delete(Long id);
}
