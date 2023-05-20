package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/home"})
    public String getHomePage(){
        return "home";
    }


    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String firstName,
                           @RequestParam String lastName){
        userService.register(firstName, lastName, username, password, password).toString();
        return "login";
    }

    @PostMapping("/login")
    public String register(@RequestParam String username,
                           @RequestParam String password ){
        userService.findByUsernameAndPassword(username, password).toString();
        return "home";
    }
}
