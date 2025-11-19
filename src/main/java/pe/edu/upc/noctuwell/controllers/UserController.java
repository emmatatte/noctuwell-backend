package pe.edu.upc.noctuwell.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwell.dtos.DTOToken;
import pe.edu.upc.noctuwell.dtos.DTOUser;
import pe.edu.upc.noctuwell.entities.User;
import pe.edu.upc.noctuwell.security.JwtUtilService;
import pe.edu.upc.noctuwell.security.UserSecurity;
import pe.edu.upc.noctuwell.services.UserService;

import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilService jwtUtilService;


    @PostMapping("/users")
    public ResponseEntity<User> insertarUser(@RequestBody User user) {
        User newUser =userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id, Authentication authentication) {
        boolean isTaylor = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_TAYLOR"));

        if (!isTaylor) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access Denied: Only ROLE_TAYLOR can access this resource :(");
        }

        DTOUser user = userService.findByIdDTO(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("users/login")
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

    @PostMapping("users/register")
    public ResponseEntity<DTOUser> registerUser(@RequestBody DTOUser dtoUser) {
        User newUser = userService.addUser(dtoUser);
        dtoUser.setPassword(""); // con fines de no exponer la contraseña grabada en la BD
        dtoUser.setId(newUser.getId());
        return new ResponseEntity<>(dtoUser, HttpStatus.CREATED);
    }



}
