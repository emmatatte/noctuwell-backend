package pe.edu.upc.noctuwellbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.noctuwellbackend.entities.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
