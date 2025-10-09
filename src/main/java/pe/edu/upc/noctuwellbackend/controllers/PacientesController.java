package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.PacientesDTO;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.PacientesService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    PacientesService pacientesService;

    @PostMapping("/insert")
    public ResponseEntity<PacientesDTO> add(@RequestBody PacientesDTO dto) {
        PacientesDTO created = pacientesService.add(dto);
        if (created == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacientesDTO> findById(@PathVariable("id") Long id) {
        PacientesDTO result = pacientesService.findById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PacientesDTO>> listAll() {
        List<PacientesDTO> list = pacientesService.listAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PacientesDTO> update(@RequestBody PacientesDTO dto) {
        PacientesDTO updated = pacientesService.edit(dto);
        if (updated == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        pacientesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
