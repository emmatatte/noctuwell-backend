package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.entities.Authority;

import java.util.List;

public interface AuthorityService {

    public Authority addAuthority(Authority authority);

    public Authority findByName(String authorityName);
    public List<Authority> getAllAuthorities();
}
