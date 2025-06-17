package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.UserStatus;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private static final int SESSION_TIMEOUT = 60*60;
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        HttpServletRequest request,
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
        // Tạo session mới để đảm bảo bảo mật
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession newSession = request.getSession(true);

        // Set session attributes
        newSession.setAttribute("user", user);
        newSession.setAttribute("userAvatar",user.getAvatarUrl());
        newSession.setAttribute("loginTime", LocalDateTime.now());
        newSession.setAttribute("userId", user.getUserId());
        newSession.setAttribute("username", user.getUsername());
        newSession.setAttribute("userRole", user.getRole());
        newSession.setMaxInactiveInterval(SESSION_TIMEOUT);

        return "redirect:/";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                System.out.println("User {"+user.getUsername()+"} logged out");
            }
            session.invalidate();
            redirectAttributes.addFlashAttribute("successMessage", "You are logged out!.");

        } catch (Exception e) {
            System.out.println("Error during logout process "+ e);
        }
        return "redirect:/login";
    }
}
