package vn.edu.fpt.BeautyCenter.controller;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

/*
 * HomePageController handles HTTP requests for the home and admin pages of the Smart Beauty System.
 * <p>
 * This controller maps the root ("/") and "/admin" endpoints, providing the necessary data
 * to the corresponding views and managing session information for the home page.
 * </p>
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
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomePageController {

    /**
     * Handles GET requests to the root URL ("/").
     * <p>
     * Adds the page title to the model, logs session information for debugging,
     * and returns the view for the home page.
     * </p>
     *
     * @param model   the model to pass attributes to the view
     * @param session the current HTTP session
     * @return the name of the view template for the home page
     */
    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        // Set the page title attribute for the home page view
        model.addAttribute("pageTitle", "Home Page");

        // Log session details for debugging purposes
        System.out.println("sessionId " + session.getId()); // Output the session ID
        System.out.println("loginTime " + session.getAttribute("loginTime")); // Output the login time
        System.out.println("user: " + session.getAttribute("user")); // Output the user object

        // Return the view template located at templates/home/index-5.html
        return "home/index-5";
    }

    /*
     * Handles GET requests to the "/admin" URL.
     * <p>
     * Adds the page title and current URI to the model and returns the admin dashboard view.
     * </p>
     *
     * @param request the HTTP servlet request
     * @param model   the model to pass attributes to the view
     * @return the name of the view template for the admin dashboard
     */
    @GetMapping({"/admin","/admin/"})
    public String admin(HttpServletRequest request, Model model, HttpSession session) {
        // Check if user is not permitted (not logged in or not admin)
        if (isNotPermit(session)) {
           return "redirect:/";
        }
        // Set the page title attribute for the admin page view
        model.addAttribute("pageTitle", "Admin Page");

        // Add the current request URI to the model for navigation or display
        model.addAttribute("currentUri", request.getRequestURI());

        // Return the view template located at templates/dashboard/home.html
        return "dashboard/home";
    }

    /**
     * Checks if current user has permission to access admin functions [1].
     * <p>
     * Validates session and user role for security [2].
     * Used across all controller methods for access control [3].
     * </p>
     *
     * @param session current HTTP session
     * @return true if user lacks permission, false if authorized
     */
    private boolean isNotPermit(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user == null || user.getRole() != Role.admin;
    }
}
