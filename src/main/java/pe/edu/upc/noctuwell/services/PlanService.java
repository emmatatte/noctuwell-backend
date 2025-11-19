package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.PlanDTO;
import pe.edu.upc.noctuwell.dtos.TopPlanDTO;
import pe.edu.upc.noctuwell.entities.Plan;

import java.util.List;

public interface PlanService {
    List<Plan> listAll();
    Plan findById(Long id);
    PlanDTO add(PlanDTO planDTO);
    Plan edit(Plan plan);
    void delete(Long id);
    List<TopPlanDTO> mostUsedPlans();
}
