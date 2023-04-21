package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findById(Long id);
    User register(String firstName, String lastName, String username, String password, String repeatPassword);
    User getAuthenticatedUser();
}
