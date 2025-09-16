package pe.edu.upc.noctuwellbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.noctuwellbackend.entities.Authority;

public interface IAuthorityRepository extends JpaRepository<Authority,Integer> {
}
