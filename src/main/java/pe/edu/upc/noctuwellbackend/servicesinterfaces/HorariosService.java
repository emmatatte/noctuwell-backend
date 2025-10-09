package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.dtos.HorariosDTO;
import java.util.List;

public interface HorariosService {
    HorariosDTO add(HorariosDTO horariosDTO);
    HorariosDTO findById(Long id);
    List<HorariosDTO> listAll();
    HorariosDTO edit(HorariosDTO horariosDTO);
    void delete(Long id);
}
