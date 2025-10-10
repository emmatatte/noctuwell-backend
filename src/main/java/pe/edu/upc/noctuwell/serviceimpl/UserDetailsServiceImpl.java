package pe.edu.upc.noctuwell.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.noctuwell.entities.User;
import pe.edu.upc.noctuwell.security.UserSecurity;
import pe.edu.upc.noctuwell.services.UserService;

@Service
public class UserDetailsServiceImpl implements  UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userFound = userService.findByUsername(username);
        return new UserSecurity(userFound);
    }
}
