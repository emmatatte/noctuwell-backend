package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.dtos.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO add(PlanDTO planDTO);
    PlanDTO findById(Long id);
    List<PlanDTO> listAll();
    PlanDTO edit(PlanDTO planDTO);
    void delete(Long id);
}
