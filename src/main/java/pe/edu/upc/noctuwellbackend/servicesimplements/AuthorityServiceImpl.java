package pe.edu.upc.noctuwellbackend.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.entities.Authority;
import pe.edu.upc.noctuwellbackend.repositories.AuthorityRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.AuthorityService;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findByName(String authorityName) {
        return authorityRepository.findByName(authorityName);
    }

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }
}
