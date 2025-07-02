package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.fpt.BeautyCenter.entity.Product;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.repository.ProductRepository;

import java.time.LocalDateTime;

@Controller
public class AddProductController {
    @Autowired
    private ProductRepository productRepository;
    
    @PostMapping("/add_product")
    public String addProduct(
            @ModelAttribute Product product,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("user");
        product.setCreated_by(user.getUserId()); // hoặc getId, tuỳ entity
        product.setCreated_at(LocalDateTime.now());
        product.setUpdated_at(LocalDateTime.now());
        productRepository.save(product);
        return "redirect:/view_products";
    }

}
