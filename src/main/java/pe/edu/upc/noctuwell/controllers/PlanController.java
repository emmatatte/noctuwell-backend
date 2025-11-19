package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.PlanDTO;
import pe.edu.upc.noctuwell.dtos.TopPlanDTO;
import pe.edu.upc.noctuwell.entities.Appointment;
import pe.edu.upc.noctuwell.entities.Plan;
import pe.edu.upc.noctuwell.services.PlanService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/noctuwell/plans")
@CrossOrigin("*")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDTO>> listAll() {
        List<PlanDTO> plans = planService.listAll().stream()
                .map(p -> new PlanDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> findById(@PathVariable Long id) {
        Plan plan = planService.findById(id);
        return (plan!=null)
                ?new ResponseEntity<>(plan, HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PlanDTO> save(@RequestBody PlanDTO planDTO) {
        planDTO = planService.add(planDTO);
        return new ResponseEntity<>(planDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Plan> edit(@RequestBody Plan plan) {
        Plan edited = planService.edit(plan);
        return new ResponseEntity<>(edited, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanDTO> delete(@PathVariable Long id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/report/most-used")
    public ResponseEntity<List<TopPlanDTO>> mostUsedPlans() {
        return new ResponseEntity<>(planService.mostUsedPlans(), HttpStatus.OK);
    }
}