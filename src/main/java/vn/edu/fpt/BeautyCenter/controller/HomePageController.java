package vn.edu.fpt.BeautyCenter.controller;
/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-8/6/2025>           <1.0>              TrungBD                      First Implement
 */
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomePageController {
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("pageTitle","Home Page");
        System.out.println("sessionId "+ session.getId());
        System.out.println("loginTime "+session.getAttribute("loginTime"));
        System.out.println("user: "+session.getAttribute("user"));
        return "home/index-5"; // trả về file home/index-5.html trong templates
    }
    @GetMapping("/admin")
    public String admin(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle","Admin Page");
        model.addAttribute("currentUri", request.getRequestURI());
        return "dashboard/home";
    }
}
