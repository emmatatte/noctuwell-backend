package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.dtos.DTOUser;
import pe.edu.upc.noctuwell.entities.User;

public interface UserService {

    public User findById (Long id);
    public DTOUser findByIdDTO (Long id);
    public User findByUsername(String username);

    public User addUser(DTOUser dtoUser);
    public User addUser(User user);




}
