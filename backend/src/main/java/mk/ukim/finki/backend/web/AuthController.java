package mk.ukim.finki.backend.web;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    @GetMapping
    public @Nullable Auth authentication(Authentication authentication) {
        if (authentication != null) {
            return new Auth(authentication.getName());
        } else {
            return null;
        }
    }
}

@AllArgsConstructor
class Auth {
    public @Nullable String user;
}
