package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.HistoryDTO;
import pe.edu.upc.noctuwell.entities.History;
import pe.edu.upc.noctuwell.services.HistoryService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/noctuwell")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/histories")
    public ResponseEntity<List<History>> listAll() {
        return new ResponseEntity<>(historyService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/histories/{id}")
    public ResponseEntity<History> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(historyService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/histories")
    public ResponseEntity<HistoryDTO> add(@RequestBody HistoryDTO historyDTO) {
        historyDTO = historyService.add(historyDTO);
        return new ResponseEntity<>(historyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/histories")
    public ResponseEntity<History> edit(@RequestBody History history) {
        History edited = historyService.edit(history);
        return new ResponseEntity<>(edited, HttpStatus.OK);
    }

    @DeleteMapping("/histories/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        historyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
