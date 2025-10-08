package pe.edu.upc.noctuwellbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.entities.Authority;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.AuthorityService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/authorities")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @PostMapping
    public ResponseEntity<Authority> addAuthority(@RequestBody Authority authority) {
        Authority newAuthority = authorityService.addAuthority(authority);
        return ResponseEntity.ok(newAuthority);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Authority> findByName(@PathVariable("name") String name) {
        Authority authority = authorityService.findByName(name);
        if (authority == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authority);
    }

    @GetMapping
    public ResponseEntity<List<Authority>> getAllAuthorities() {
        List<Authority> authorities = authorityService.getAllAuthorities();
        return ResponseEntity.ok(authorities);
    }
}
