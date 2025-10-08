package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.PlanDTO;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.PlanService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/plans")
public class PlanController {
    @Autowired
    PlanService planService;

    @PostMapping("/insert")
    public ResponseEntity<PlanDTO> add(@RequestBody PlanDTO planDTO) {
        PlanDTO newPlan = planService.add(planDTO);
        if (newPlan == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newPlan, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> findById(@PathVariable("id") Long id) {
        PlanDTO plan = planService.findById(id);
        if (plan == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PlanDTO>> listAll() {
        List<PlanDTO> planes = planService.listAll();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<PlanDTO> update(@RequestBody PlanDTO planDTO) {
        PlanDTO updatedPlan = planService.edit(planDTO);
        if (updatedPlan == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
