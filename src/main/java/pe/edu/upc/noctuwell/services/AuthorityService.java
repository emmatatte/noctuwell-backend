package pe.edu.upc.noctuwell.services;

import pe.edu.upc.noctuwell.entities.Authority;

public interface AuthorityService {

    public Authority addAuthority(Authority authority);

    public Authority findByName(String authorityName);

}
