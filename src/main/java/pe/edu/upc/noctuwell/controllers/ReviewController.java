package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.ReviewDTO;
import pe.edu.upc.noctuwell.entities.Review;
import pe.edu.upc.noctuwell.services.ReviewService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/noctuwell")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> listAll() {
        return new ResponseEntity<>(reviewService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reviewService.findById(id), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<ReviewDTO> add(@RequestBody ReviewDTO reviewDTO) {
        reviewDTO = reviewService.add(reviewDTO);
        return new ResponseEntity<>(reviewDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
