package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.BeautyCenter.entity.Product;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ViewProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin/product")
    public String getViewProduct(
            @RequestParam(defaultValue = "0") int page, // <-- đổi thành 0-based cho an toàn
            Model model,
            HttpSession session,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws Exception {
        User user = (User) session.getAttribute("user");
        if (user != null && !user.getRole().name().equals("customer")) {
            int pageSize = 10; // số sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(page, pageSize); // Không -1 nữa!
            Page<Product> productPage = productRepository.findAll(pageable);

            model.addAttribute("products", productPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", productPage.getTotalPages());
            return "admin.products/viewProduct";
        } else if (user != null && user.getRole().name().equals("customer")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            request.getRequestDispatcher("/error").forward(request, response);
            return null;
        }
        return "redirect:/login";
    }

    @GetMapping("admin/product/{id}")
    public String productDetail(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id:" + id));
        model.addAttribute("product", product);
        return "admin.products/productDetail";
    }

    @PostMapping("admin/product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa sản phẩm.");
        }
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute Product product, HttpSession session) {
        // Lấy user từ session
        User currentUser = (User) session.getAttribute("user"); // hoặc "user"
        if (currentUser != null) {
            product.setCreated_by(currentUser.getUserId()); // hoặc .getId() tùy DB
        } else {
            // Nếu không có user, chuyển hướng về login (tùy logic của bạn)
            return "redirect:/login";
        }
        // Gán created_at (vì có thể trường này cũng NOT NULL)
        product.setCreated_at(LocalDateTime.now());
        product.setUpdated_at(LocalDateTime.now());
        productRepository.save(product);
        return "redirect:/admin/product";
    }



}

