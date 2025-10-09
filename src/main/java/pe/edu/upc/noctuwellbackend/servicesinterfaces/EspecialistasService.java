package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.dtos.EspecialistasDTO;
import java.util.List;

public interface EspecialistasService {
    EspecialistasDTO add(EspecialistasDTO especialistasDTO);
    EspecialistasDTO findById(Long id);
    List<EspecialistasDTO> listAll();
    EspecialistasDTO edit(EspecialistasDTO especialistasDTO);
    void delete(Long id);
}
