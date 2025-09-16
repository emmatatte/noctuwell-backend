package pe.edu.upc.noctuwellbackend.servicesinterfaces;

import pe.edu.upc.noctuwellbackend.entities.Authority;

import java.util.List;

public interface IAuthorityService {
    List<Authority> list();
    void insert(Authority authority);
    void update(Authority authority);
    void delete(int idAuthority);
    public Authority listId(int idAuthority);
}
