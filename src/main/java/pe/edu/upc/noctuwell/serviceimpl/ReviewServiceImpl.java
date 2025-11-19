package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.dtos.ReviewDTO;
import pe.edu.upc.noctuwell.entities.Patient;
import pe.edu.upc.noctuwell.entities.Review;
import pe.edu.upc.noctuwell.entities.Specialist;
import pe.edu.upc.noctuwell.repositories.ReviewRepository;
import pe.edu.upc.noctuwell.services.PatientService;
import pe.edu.upc.noctuwell.services.ReviewService;
import pe.edu.upc.noctuwell.services.SpecialistService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    SpecialistService specialistService;

    @Override
    public ReviewDTO add(ReviewDTO reviewDTO) {
        Specialist specialistFound = specialistService.findById(reviewDTO.getSpecialistId());
        Patient patientFound = patientService.findById(reviewDTO.getPatientId());
        if(specialistFound == null || patientFound == null){
            System.out.println("Specialist or Patient not found");
            return null;
        }
        Review review = new Review(null,
                reviewDTO.getComment(),
                reviewDTO.getFecha(),
                reviewDTO.getCalificacion(),
                patientFound,
                specialistFound
        );
        review = reviewRepository.save(review);
        reviewDTO.setId(review.getId());
        return reviewDTO;
    }

    @Override
    public void delete(Long id) {
        Review found= findById(id);
        if(found != null){
            reviewRepository.deleteById(id);
        }
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<Review> listAll() {
        return reviewRepository.findAll();
    }
}
