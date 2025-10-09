package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.EspecialistasDTO;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.EspecialistasService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/especialistas")
public class EspecialistasController {

    @Autowired
    EspecialistasService especialistasService;

    @PostMapping("/insert")
    public ResponseEntity<EspecialistasDTO> add(@RequestBody EspecialistasDTO dto) {
        EspecialistasDTO created = especialistasService.add(dto);
        if (created == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialistasDTO> findById(@PathVariable("id") Long id) {
        EspecialistasDTO result = especialistasService.findById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EspecialistasDTO>> listAll() {
        List<EspecialistasDTO> list = especialistasService.listAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EspecialistasDTO> update(@RequestBody EspecialistasDTO dto) {
        EspecialistasDTO updated = especialistasService.edit(dto);
        if (updated == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        especialistasService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
