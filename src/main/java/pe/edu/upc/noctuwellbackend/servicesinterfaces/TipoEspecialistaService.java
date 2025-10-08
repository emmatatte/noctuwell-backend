package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.dtos.TipoEspecialistaDTO;

import java.util.List;

public interface TipoEspecialistaService {
    TipoEspecialistaDTO add(TipoEspecialistaDTO tipoEspecialistaDTO);
    TipoEspecialistaDTO findById(Long id);
    List<TipoEspecialistaDTO> listAll();
    TipoEspecialistaDTO edit(TipoEspecialistaDTO tipoEspecialistaDTO);
    void delete(Long id);
}
