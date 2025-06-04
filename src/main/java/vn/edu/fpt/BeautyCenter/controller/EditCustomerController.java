package vn.edu.fpt.BeautyCenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit_customer")
public class EditCustomerController {
    @GetMapping
    public String editCustomer() {
        return "customer/editCustomer";
    }
}
