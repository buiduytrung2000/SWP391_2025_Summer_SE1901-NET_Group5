package vn.edu.fpt.BeautyCenter.controller;

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
    @GetMapping
    public String home(Model model) {
        model.addAttribute("pageTitle","Home Page");
        return "home/index-5"; // trả về file home/index-5.html trong templates
    }
}
