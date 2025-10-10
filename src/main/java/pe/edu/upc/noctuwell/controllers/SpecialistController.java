package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.PatientPostDTO;
import pe.edu.upc.noctuwell.dtos.SpecialistPostDTO;
import pe.edu.upc.noctuwell.entities.Specialist;
import pe.edu.upc.noctuwell.services.SpecialistService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/noctuwell/specialists")
@CrossOrigin("*")
public class SpecialistController {

    @Autowired
    private SpecialistService specialistService;

    @GetMapping
    public ResponseEntity<List<SpecialistPostDTO>> listAll() {
        List<SpecialistPostDTO> specialists = specialistService.listAll().stream()
                .map(s -> new SpecialistPostDTO(
                        s.getId(),
                        s.getFirstName(),
                        s.getLastName(),
                        s.getPhone(),
                        s.getCertification(),
                        s.getDescription(),
                        s.getExperience(),
                        s.getUser().getId()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(specialists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialist> findById(@PathVariable Long id) {
        Specialist specialist = specialistService.findById(id);
        return (specialist != null)
                ? new ResponseEntity<>(specialist, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SpecialistPostDTO> add(@RequestBody SpecialistPostDTO specialistpostDTO) {
        specialistpostDTO = specialistService.add(specialistpostDTO);
        return new ResponseEntity<>(specialistpostDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        specialistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
