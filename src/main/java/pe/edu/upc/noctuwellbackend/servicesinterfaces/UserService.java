package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.dtos.DTOUser;
import pe.edu.upc.noctuwellbackend.entities.User;

public interface UserService {

    public User findById (Long id);
    public DTOUser findByIdDTO (Long id);
    public User findByUsername(String username);

    public User addUser(DTOUser dtoUser);
    public User addUser(User user);
    //aqui se va a usar un DTO



}
