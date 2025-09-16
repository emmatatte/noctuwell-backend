package pe.edu.upc.noctuwellbackend.servicesimplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwellbackend.entities.Authority;
import pe.edu.upc.noctuwellbackend.repositories.IAuthorityRepository;
import pe.edu.upc.noctuwellbackend.servicesinterfaces.IAuthorityService;

import java.util.List;
@Service
public class AuthorityServiceImplement implements IAuthorityService {

    @Autowired
    private IAuthorityRepository aR;

    @Override
    public List<Authority> list() {
        return aR.findAll();
    }

    @Override
    public void insert(Authority authority) {
        aR.save(authority);
    }

    @Override
    public void update(Authority authority) {
        aR.save(authority);
    }

    @Override
    public void delete(int idAuthority) {
        aR.deleteById(idAuthority);
    }

    @Override
    public Authority listId(int idAuthority) {
        return aR.findById(idAuthority).orElse(new Authority());
    }
}
