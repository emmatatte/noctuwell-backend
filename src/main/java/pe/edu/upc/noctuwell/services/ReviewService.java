package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.ReviewDTO;
import pe.edu.upc.noctuwell.entities.Review;

import java.util.List;

public interface ReviewService {
    ReviewDTO add(ReviewDTO reviewDTO);
    void delete(Long id);
    Review findById(Long id);
    List<Review> listAll();
}
