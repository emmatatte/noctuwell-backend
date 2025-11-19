package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.noctuwell.entities.TypeSpecialist;

@Repository
public interface TypeSpecialistRepository extends JpaRepository<TypeSpecialist,Long> {
}
