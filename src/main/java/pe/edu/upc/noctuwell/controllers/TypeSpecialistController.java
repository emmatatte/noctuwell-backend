package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.TypeSpecialistDTO;
import pe.edu.upc.noctuwell.entities.Plan;
import pe.edu.upc.noctuwell.entities.TypeSpecialist;
import pe.edu.upc.noctuwell.services.TypeSpecialistService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/noctuwell/typespecialists")
@CrossOrigin("*")
public class TypeSpecialistController {
    @Autowired
    private TypeSpecialistService typeSpecialistService;

    @GetMapping
    public ResponseEntity<List<TypeSpecialistDTO>> listAll() {
        List<TypeSpecialistDTO> typeSpecialits = typeSpecialistService.listAll().stream()
                .map(t -> new TypeSpecialistDTO(
                        t.getId(),
                        t.getName(),
                        t.getDescription()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(typeSpecialits, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TypeSpecialist> findById(@PathVariable Long id) {
        TypeSpecialist typeSpecialist = typeSpecialistService.findById(id);
        return (typeSpecialist!=null)
                ? new ResponseEntity<>(typeSpecialist, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<TypeSpecialistDTO> add(@RequestBody TypeSpecialistDTO typeSpecialistDTO) {
        typeSpecialistDTO = typeSpecialistService.add(typeSpecialistDTO);
        return new ResponseEntity<>(typeSpecialistDTO, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<TypeSpecialist> edit(@RequestBody TypeSpecialist typeSpecialist) {
        TypeSpecialist edited = typeSpecialistService.edit(typeSpecialist);
        return new ResponseEntity<>(edited, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeSpecialistDTO> delete(@PathVariable Long id) {
        typeSpecialistService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}