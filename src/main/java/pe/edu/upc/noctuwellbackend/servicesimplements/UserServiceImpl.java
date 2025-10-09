package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.dtos.DTOUser;
import pe.edu.upc.noctuwellbackend.entities.Authority;
import pe.edu.upc.noctuwellbackend.entities.User;
import pe.edu.upc.noctuwellbackend.exceptions.KeyRepeatedDataExeception;
import pe.edu.upc.noctuwellbackend.exceptions.ResourceNotFoundException;
import pe.edu.upc.noctuwellbackend.repositories.UserRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.AuthorityService;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;


    /* Viene de UserService */

    @Override
    public User findById(Long id) {
        User userFound = userRepository.findById(id).orElse(null);
        if(userFound == null){
            throw new ResourceNotFoundException("User with Id: "+ id+" not found");
        }
        return userFound;
    }

    @Override
    public DTOUser findByIdDTO(Long id) {
        User user = findById(id);
        DTOUser userFound = new DTOUser(user.getId(), user.getUsername(), user.getPassword(),
                        user.getAuthorities()
                        .stream()
                        .map( a-> a.getName())
                        .collect(Collectors.joining(";","",""))

                );
        return userFound;
    }

    @Override
    public User findByUsername(String username) {
        User userFound = userRepository.findByUsername(username);
        if(userFound == null){
            throw new ResourceNotFoundException("User with Username: "+ username+" not found");
        }
        return userFound;
    }

    private List<Authority>  authoritiesFromString(String authorities){
        List<Authority> authoritiesList = new ArrayList<>();
        List<String> authoritiesStringList = Arrays.stream(authorities.split(";")).toList();
        for(String authorityString : authoritiesStringList){
            Authority authority = authorityService.findByName(authorityString);
            if (authority != null){
                authoritiesList.add(authority);
            }
        }
        return authoritiesList;
    }

    @Override
    public User addUser(DTOUser dtoUser) {
        User newUser = new User();
        newUser.setUsername(dtoUser.getUsername());
        newUser.setPassword(dtoUser.getPassword());
        newUser.setEnabled(true);
        List<Authority> authorityList = authoritiesFromString(dtoUser.getAuthorities());
        newUser.setAuthorities(authorityList);
        return addUser(newUser);
    }


    @Override
    public User addUser(User user) {

        User userFound = userRepository.findByUsername(user.getUsername());
        if(userFound != null){
            throw new KeyRepeatedDataExeception("Username: "+ user.getUsername()+" is already registeted");
        }

        //Paso 1: Validar si el username y el password cumplen con los requisitos: minimo, maximo, tipos carecteres

        //Paso 2: Encriptar el password
        user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword())  );

        //Paso 3: Actualizar los campos adicionales segun tu logica de negocio
        user.setEnabled(true);

        return userRepository.save(user);
    }


}
