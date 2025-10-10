package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.PatientPostDTO;
import pe.edu.upc.noctuwell.entities.Patient;
import pe.edu.upc.noctuwell.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/noctuwell/patients")
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> listAll() {
        return new ResponseEntity<>(patientService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return (patient != null)
                ? new ResponseEntity<>(patient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<PatientPostDTO> add(@RequestBody PatientPostDTO patientpostDTO) {
        patientpostDTO = patientService.add(patientpostDTO);
        return new ResponseEntity<>(patientpostDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
