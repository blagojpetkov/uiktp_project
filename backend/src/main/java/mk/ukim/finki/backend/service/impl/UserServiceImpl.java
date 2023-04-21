package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }


    @Override
    public User register(String firstName, String lastName, String username, String password, String repeatPassword) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || username == null || username.isEmpty()
                || password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty())
            throw new RuntimeException("Invalid Arguments Exception");
        if (!password.equals(repeatPassword))
            throw new RuntimeException("Passwords do not match");
        if (userRepository.findByUsername(username).isPresent())
            throw new RuntimeException("Username already exists");

        return userRepository.save(new User(username, passwordEncoder.encode(password), firstName, lastName));
    }

    @Override
    public User getAuthenticatedUser() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}

