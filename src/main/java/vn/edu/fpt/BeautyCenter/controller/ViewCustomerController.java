package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.service.S3Service;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.io.IOException;

@Controller
@RequestMapping("/view_profile")
public class ViewCustomerController {

    @GetMapping
    public String viewCustomerProfile(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        int lastNameIndex=0;
        if(user != null) {
            String firstName= user.getFullName().split(" ")[0];
            for(String word: user.getFullName().split(" ")) {
                lastNameIndex++;
            }
            String lastName= user.getFullName().split(" ")[lastNameIndex-1];
            model.addAttribute("user", user);
            model.addAttribute("firstname", lastName);
            model.addAttribute("lastname", firstName);
            model.addAttribute("fullName", user.getFullName());
            model.addAttribute("role", user.getRole());
            return "customer/viewCustomer";
        }
        return "redirect:/login";
    }

}
