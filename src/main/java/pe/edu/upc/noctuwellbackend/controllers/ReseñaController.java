package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.ReseñaDTO;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.ReseñaService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/reseñas")
public class ReseñaController {

    @Autowired
    ReseñaService reseñaService;

    @PostMapping("/insert")
    public ResponseEntity<ReseñaDTO> add(@RequestBody ReseñaDTO dto) {
        ReseñaDTO created = reseñaService.add(dto);
        if (created == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReseñaDTO> findById(@PathVariable("id") Long id) {
        ReseñaDTO result = reseñaService.findById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReseñaDTO>> listAll() {
        List<ReseñaDTO> list = reseñaService.listAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ReseñaDTO> update(@RequestBody ReseñaDTO dto) {
        ReseñaDTO updated = reseñaService.edit(dto);
        if (updated == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reseñaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
