package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.dtos.PlanDTO;
import pe.edu.upc.noctuwellbackend.entities.Plan;
import pe.edu.upc.noctuwellbackend.repositories.PlanRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.PlanService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    private Plan convertToEntity(PlanDTO dto) {
        Plan plan = new Plan();
        plan.setId(dto.getId());
        plan.setNombre(dto.getNombre());
        plan.setDescripcion(dto.getDescripcion());
        plan.setPrecio(dto.getPrecio());
        return plan;
    }

    private PlanDTO convertToDTO(Plan entity) {
        PlanDTO dto = new PlanDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        return dto;
    }

    @Override
    public PlanDTO add(PlanDTO planDTO) {
        Plan plan = convertToEntity(planDTO);
        Plan savedPlan = planRepository.save(plan);
        return convertToDTO(savedPlan);
    }

    @Override
    public PlanDTO findById(Long id) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan == null) {
            return null;
        }
        return convertToDTO(plan);
    }

    @Override
    public List<PlanDTO> listAll() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlanDTO edit(PlanDTO planDTO) {
        Plan existingPlan = planRepository.findById(planDTO.getId()).orElse(null);
        if (existingPlan == null) {
            return null;
        }

        if (planDTO.getNombre() != null) {
            existingPlan.setNombre(planDTO.getNombre());
        }
        if (planDTO.getDescripcion() != null) {
            existingPlan.setDescripcion(planDTO.getDescripcion());
        }
        if (planDTO.getPrecio() != null) {
            existingPlan.setPrecio(planDTO.getPrecio());
        }

        Plan updatedPlan = planRepository.save(existingPlan);
        return convertToDTO(updatedPlan);
    }

    @Override
    public void delete(Long id) {
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
        }
    }
}
