package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.fpt.BeautyCenter.entity.Product;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.repository.ProductRepository;

import java.time.LocalDateTime;

@Controller
public class EditProductController {

    @Autowired
    private ProductRepository productRepository;
    // GET: Trang edit
    @GetMapping("admin/product/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id:" + id));
        model.addAttribute("product", product);
        return "admin/productEdit";
    }

    // POST: Xử lý cập nhật
    @PostMapping("/admin/product/edit/{id}") // <-- thêm /admin
    public String updateProduct(
            @PathVariable("id") Integer id,
            @ModelAttribute("product") Product productForm
    ) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id:" + id));
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setSku(productForm.getSku());
        product.setQuantity(productForm.getQuantity());
        product.setUpdated_at(LocalDateTime.now());
        productRepository.save(product);
        // Redirect đúng về trang quản lý sản phẩm
        return "redirect:/admin/product";
    }



}
