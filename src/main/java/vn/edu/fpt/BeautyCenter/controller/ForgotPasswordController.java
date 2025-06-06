package vn.edu.fpt.BeautyCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.service.UserService;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {
    private final UserService userService;

    @GetMapping("/confirm-otp")
    public String showConfirmOtpPage() {
        return "auth/confirm-otp";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam (name = "email") String email, Model model) {
        if(!userService.existsByEmail(email)){
            model.addAttribute("error", "Email not found");
            return "auth/login";
        }
        userService.sendPasswordResetEmail(email);
        return "auth/confirm-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam(name = "otp") String otp, Model model) {
        User user = userService.verifyOtp(otp);
        if(user == null) {
            model.addAttribute("error", "Invalid OTP");
            return "auth/confirm-otp";
        }
        model.addAttribute("email", user.getEmail());
        return "auth/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "password") String password,
                                    @RequestParam(name = "confirmPassword") String confirmPassword,
                                 Model model) {
        if(!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("email", email);
            return "auth/reset-password";
        }
        userService.resetPassword(email, password);

        return "redirect:/login";
    }
}
