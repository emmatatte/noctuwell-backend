package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.DiagnosisDTO;
import pe.edu.upc.noctuwell.entities.Diagnosis;
import pe.edu.upc.noctuwell.services.DiagnosisService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/noctuwell")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping("/diagnoses")
    public ResponseEntity<List<Diagnosis>> listAll() {
        return new ResponseEntity<>(diagnosisService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/diagnoses/{id}")
    public ResponseEntity<Diagnosis> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(diagnosisService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/diagnoses")
    public ResponseEntity<DiagnosisDTO> add(@RequestBody DiagnosisDTO diagnosisDTO) {
        diagnosisDTO = diagnosisService.add(diagnosisDTO);
        return new ResponseEntity<>(diagnosisDTO, HttpStatus.CREATED);
    }

    @PutMapping("/diagnoses")
    public ResponseEntity<Diagnosis> edit(@RequestBody Diagnosis diagnosis) {
        Diagnosis edited = diagnosisService.edit(diagnosis);
        return new ResponseEntity<>(edited, HttpStatus.OK);
    }

    @DeleteMapping("/diagnoses/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        diagnosisService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}