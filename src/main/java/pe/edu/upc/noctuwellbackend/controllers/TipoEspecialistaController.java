package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.TipoEspecialistaDTO;
import pe.edu.upc.noctuwellbackend.entities.TipoEspecialista;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.TipoEspecialistaService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/tipoespecialistas")
public class TipoEspecialistaController {
    @Autowired
    TipoEspecialistaService tipoEspecialistaService;

    @PostMapping("/insert")
    public ResponseEntity<TipoEspecialistaDTO> add(@RequestBody TipoEspecialistaDTO tipoEspecialistaDTO) {
        TipoEspecialistaDTO newTipo = tipoEspecialistaService.add(tipoEspecialistaDTO);
        if (newTipo == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newTipo, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TipoEspecialistaDTO> findById(@PathVariable("id") Long id) {
        TipoEspecialistaDTO tipo = tipoEspecialistaService.findById(id);
        if (tipo == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tipo, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TipoEspecialistaDTO>> listAll() {
        List<TipoEspecialistaDTO> tipos = tipoEspecialistaService.listAll();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<TipoEspecialistaDTO> update(@RequestBody TipoEspecialistaDTO tipoEspecialistaDTO) {
        TipoEspecialistaDTO updated = tipoEspecialistaService.edit(tipoEspecialistaDTO);
        if (updated == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        tipoEspecialistaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
