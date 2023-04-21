package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class CourseController {
    private final UserRepository userRepository;
    private final UserService userService;

    public CourseController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping("/test")
    public String test(){
        userRepository.save(new User("username123", "password", "firstname", "lastname"));
        return "helloworld";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String firstName,
                           @RequestParam String lastName){
        return userService.register(firstName, lastName, username, password, password).toString();

    }
}
