package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        if (statusCode != null && statusCode == 404) {
            return "errors/error404";
        }
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }

}
