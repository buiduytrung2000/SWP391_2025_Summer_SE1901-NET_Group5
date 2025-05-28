package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.UserStatus;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        Model model) {

        Optional<User> userOptional = userService.findByUsernameAndPassword(username, password);
        if(userOptional.isEmpty()){
            model.addAttribute("error", "Invalid username or password");
            return "auth/login";
        }
        User user = userOptional.get();
        if(user.getStatus().equals(UserStatus.inactive)){
            model.addAttribute("error", "Your account is not activated. Please check your email for activation link.");
            return "auth/login";
        }
        httpSession.setAttribute("user", user);
        return "redirect:/";

    }
}
