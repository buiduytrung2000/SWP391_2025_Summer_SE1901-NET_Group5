package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.repository.UserRepository;
import vn.edu.fpt.BeautyCenter.service.UserService;

@Controller
@RequestMapping("/edit_customer")
public class EditCustomerController {
    @Autowired
    UserService userService;
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
    public String editCustomer(@ModelAttribute User user, HttpSession session) {
        // Lấy user thật sự từ session (chắc chắn đúng user đang đăng nhập)
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        sessionUser.setAvatarUrl(user.getAvatarUrl());
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

        session.setAttribute("user", sessionUser); // Cập nhật lại session nếu muốn

        return "customer/editCustomer";
    }
}
