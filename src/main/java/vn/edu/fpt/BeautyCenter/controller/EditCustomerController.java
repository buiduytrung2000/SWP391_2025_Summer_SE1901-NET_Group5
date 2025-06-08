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

@Controller
@RequestMapping("/edit_customer")
public class EditCustomerController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String gotoEditCustomer(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user", user);    // BẮT BUỘC dòng này!

            return "customer/editCustomer";
        }
        return "redirect:/login";
    }

    @PostMapping
    public String editCustomer(@ModelAttribute User user, HttpSession session) {
        // Có thể cập nhật lại session
        session.setAttribute("user", user);
        // Lưu xuống DB nếu cần
        userRepository.save(user);
        return "customer/editCustomer"; // Hoặc trang bạn muốn
    }
}
