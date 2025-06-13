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
@RequestMapping("/edit_profile")
public class EditUserController {
    @Autowired
    UserService userService;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    public String gotoEditCustomer(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user", user);

            return "customer/editCustomer";
        }
        return "redirect:/login";
    }

    @PostMapping
    public String editCustomer(@ModelAttribute User user, HttpSession session, @RequestParam("avatar") MultipartFile file) {
        // Lấy user thật sự từ session (chắc chắn đúng user đang đăng nhập)
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        if (file != null && !file.isEmpty()) {
            try {
                String url = s3Service.uploadFile(file);
                sessionUser.setAvatarUrl(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getFullName() != null && !user.getFullName().isEmpty())
            sessionUser.setFullName(user.getFullName());
        if(user.getPhone() != null && !user.getPhone().isEmpty())
            sessionUser.setPhone(user.getPhone());
        if(user.getEmail() != null && !user.getEmail().isEmpty())
            sessionUser.setEmail(user.getEmail());
        if(user.getPassword() != null && !user.getPassword().isEmpty())
            sessionUser.setPassword(user.getPassword());
         if(user.getUsername() != null && !user.getUsername().isEmpty())
             sessionUser.setUsername(user.getUsername());
        userService.updateUser(sessionUser);

        User updatedUser = userService.getUserById(sessionUser.getUserId());
        session.setAttribute("user", updatedUser);

        return "customer/editCustomer";
    }
}
