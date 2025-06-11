package vn.edu.fpt.BeautyCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.fpt.BeautyCenter.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "fullname") String fullName,
                               @RequestParam(name = "username") String username,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "phone") String phone,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "confirmPassword") String confirmPassword,
                               Model model) {
        if(userService.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists");
            return "auth/register";
        }
        if(userService.existsByEmail(email)) {
            model.addAttribute("error", "Email already exists");
            return "auth/register";
        }
        if(userService.existsByPhone(phone)) {
            model.addAttribute("error", "Phone number already exists");
            return "auth/register";
        }
        if(!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "auth/register";
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
            model.addAttribute("error", "Password must be at least 8 characters and include uppercase, lowercase, and number");
            return "auth/register";
        }

        userService.registerUser(fullName, username, email, phone, password);
        return "redirect:/login";
    }

    @GetMapping("/register/verify")
    public String verifyEmail(@RequestParam(name = "email") String email) {
        userService.verifyEmail(email);
        return "redirect:/login";
    }
}
