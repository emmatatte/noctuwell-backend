package pe.edu.upc.noctuwell.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.noctuwell.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

}
