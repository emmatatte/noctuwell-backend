package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.noctuwell.entities.Specialist;

import java.util.List;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    @Query(value = """
            SELECT s.* 
            FROM specialists s
            JOIN reviews r ON s.id = r.specialist_id
            GROUP BY s.id
            ORDER BY AVG(r.calificacion) DESC
            LIMIT :limit
            """, nativeQuery = true)
    List<Specialist> findTopRatedSpecialists(@Param("limit") int limit);

    @Query(value = "SELECT ts.name AS type_name, SUM(s.experience) AS total_experience " +
            "FROM specialists s " +
            "JOIN type_specialists ts ON s.type_especialist_id = ts.id " +
            "GROUP BY ts.name ORDER BY total_experience DESC LIMIT 1",
            nativeQuery = true)
    Object findTypeSpecialistWithMostExperience();

}
