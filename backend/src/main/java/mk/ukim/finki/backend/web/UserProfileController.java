package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam("currentPassword") String currentPassword,
                                @RequestParam("newUsername") String newUsername,
                                @RequestParam("newPassword") String newPassword) {
        userService.updateUserDetails(currentPassword, newUsername, newPassword);
        return "redirect:/profile";
    }

    @PostMapping("/profile/edit")
    public String updateProfileInfo(@RequestParam("currentUsername") String currentUsername,
                                    @RequestParam("newUsername") String newUsername,
                                    @RequestParam("newFirstName") String newFirstName,
                                    @RequestParam("newLastName") String newLastName) {
        userService.updateProfileInfo(currentUsername, newUsername, newFirstName, newLastName);
        return "redirect:/profile";
    }
}
