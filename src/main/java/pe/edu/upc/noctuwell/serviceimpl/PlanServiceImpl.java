package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.PlanDTO;
import pe.edu.upc.noctuwell.dtos.TopPlanDTO;
import pe.edu.upc.noctuwell.entities.Appointment;
import pe.edu.upc.noctuwell.entities.Plan;
import pe.edu.upc.noctuwell.repositories.PlanRepository;
import pe.edu.upc.noctuwell.services.PlanService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanRepository planRepository;


    @Override
    public List<Plan> listAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan findById(Long id) {
        return planRepository.findById(id).orElse(null);
    }

    @Override
    public PlanDTO add(PlanDTO planDTO) {
        if (planDTO.getName() == null || planDTO.getName().isBlank()) {
            return null;
        }
        if (planDTO.getPrice() == null || planDTO.getPrice() < 0) {
            return null;
        }
        Plan plan = new Plan(null,
                planDTO.getName(),
                planDTO.getDescription(),
                planDTO.getPrice(),
                null);
        plan = planRepository.save(plan);
        planDTO.setId(plan.getId());
        return planDTO;
    }

    @Override
    public Plan edit(Plan plan) {
        Plan found = findById(plan.getId());
        if (found == null) return null;

        if (plan.getName() != null)
            found.setName(plan.getName());
        if (plan.getDescription() != null)
            found.setDescription(plan.getDescription());
        if (plan.getPrice() != null && plan.getPrice() >= 0)
            found.setPrice(plan.getPrice());

        return planRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public List<TopPlanDTO> mostUsedPlans() {
        return planRepository.findMostUsedPlansRaw()
                .stream()
                .map(record -> new TopPlanDTO(
                        record[0] == null ? null : ((Number) record[0]).longValue(),
                        (String) record[1],
                        record[2] == null ? 0L : ((Number) record[2]).longValue()
                ))
                .collect(Collectors.toList());
    }
}
