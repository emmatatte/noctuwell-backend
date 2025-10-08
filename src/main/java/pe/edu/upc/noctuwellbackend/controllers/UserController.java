package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.DTOToken;
import pe.edu.upc.noctuwellbackend.dtos.DTOUser;
import pe.edu.upc.noctuwellbackend.entities.User;
import pe.edu.upc.noctuwellbackend.security.JwtUtilService;
import pe.edu.upc.noctuwellbackend.security.UserSecurity;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.UserService;

import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;


    @PostMapping("/insert")
    public ResponseEntity<User> insertarUser(@RequestBody User user) {
        User newUser =userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DTOUser> findById(@PathVariable("id") Long id) {
        DTOUser user = userService.findByIdDTO(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<DTOToken> login(@RequestBody DTOUser user) {

        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserSecurity userSecurity = (UserSecurity)userDetailsService.loadUserByUsername(user.getUsername());

        String jwt = jwtUtilService.generateToken(userSecurity);
        Long id = userSecurity.getUser().getId();
        String authorities = userSecurity.getUser().getAuthorities()
                .stream()
                .map( a-> a.getName())
                .collect(Collectors.joining(";","",""));

        return new ResponseEntity<>(new DTOToken(jwt,id, authorities), HttpStatus.OK);
    }


/*
    // Alternativa de Registro de Usuarios devolviendo Entity

    @PostMapping("users/register")
    public ResponseEntity<User> registerUser(@RequestBody DTOUser dtoUser) {
        User newUser = userService.addUser(dtoUser);
        //newUser.setPassword(""); // con fines de no exponer la contraseña grabada en la BD
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

*/

    // Alternativa de Registro de Usuarios devolviendo DTO

    @PostMapping("/users/register")
    public ResponseEntity<DTOUser> registerUser(@RequestBody DTOUser dtoUser) {
        User newUser = userService.addUser(dtoUser);
        dtoUser.setPassword(""); // con fines de no exponer la contraseña grabada en la BD
        dtoUser.setId(newUser.getId());
        return new ResponseEntity<>(dtoUser, HttpStatus.CREATED);
    }




}
