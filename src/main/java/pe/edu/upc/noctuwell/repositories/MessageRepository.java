package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.noctuwell.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
