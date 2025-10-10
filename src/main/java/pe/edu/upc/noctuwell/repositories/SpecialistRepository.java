package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.noctuwell.entities.Specialist;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
}
