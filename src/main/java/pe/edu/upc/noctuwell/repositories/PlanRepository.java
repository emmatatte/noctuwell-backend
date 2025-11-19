package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.noctuwell.entities.Plan;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query(value = """
    SELECT 
        sub.plan_id        AS planId,
        sub.plan_name      AS planName,
        sub.total_patients AS totalPatients
    FROM (
        SELECT 
            p.id          AS plan_id,
            p.name        AS plan_name,
            COUNT(pa.id)  AS total_patients
        FROM 
            plans p
            JOIN patients pa ON pa.plan_id = p.id
        GROUP BY 
            p.id, p.name
    ) sub
    WHERE sub.total_patients = (
        SELECT 
            MAX(t.total_patients)
        FROM (
            SELECT 
                COUNT(pa2.id) AS total_patients
            FROM 
                plans p2
                JOIN patients pa2 ON pa2.plan_id = p2.id
            GROUP BY 
                p2.id, p2.name
        ) t
    )
    ORDER BY 
        sub.plan_name
    """, nativeQuery = true)
    List<Object[]> findMostUsedPlansRaw();
}
