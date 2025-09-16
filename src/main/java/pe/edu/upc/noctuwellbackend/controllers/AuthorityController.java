package pe.edu.upc.noctuwellbackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.noctuwellbackend.dtos.AuthorityDTO;
import pe.edu.upc.noctuwellbackend.entities.Authority;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.IAuthorityService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {
    @Autowired
    private IAuthorityService aS;

    public AuthorityController() {
    }

    @GetMapping
    public List<AuthorityDTO> listar() {
        return aS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, AuthorityDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/registrar")
    public void registrar(@RequestBody AuthorityDTO aDTO) {
        ModelMapper m = new ModelMapper();
        Authority a = m.map(aDTO, Authority.class);
        aS.insert(a);
    }
}
