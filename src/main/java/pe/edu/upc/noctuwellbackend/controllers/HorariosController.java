package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.HorariosDTO;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.HorariosService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/horarios")
public class HorariosController {

    @Autowired
    HorariosService horariosService;

    @PostMapping("/insert")
    public ResponseEntity<HorariosDTO> add(@RequestBody HorariosDTO dto) {
        HorariosDTO created = horariosService.add(dto);
        if (created == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorariosDTO> findById(@PathVariable("id") Long id) {
        HorariosDTO result = horariosService.findById(id);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HorariosDTO>> listAll() {
        List<HorariosDTO> list = horariosService.listAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HorariosDTO> update(@RequestBody HorariosDTO dto) {
        HorariosDTO updated = horariosService.edit(dto);
        if (updated == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        horariosService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
