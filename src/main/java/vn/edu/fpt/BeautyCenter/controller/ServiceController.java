package vn.edu.fpt.BeautyCenter.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.service.ServiceService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {
    ServiceService serviceService;

    // Hiển thị form add service
    @GetMapping("/add")
    public String ShowAddForm(Model model) {
        model.addAttribute("pageTitle","Add New Service");
        return "admin/services/add";
    }
    // Xử lý add service
    @PostMapping("/add")
    public String save(@ModelAttribute @Valid ServiceCreationRequest request) {
        serviceService.createService(request);
        return "redirect:/admin/services";
    }

    // Xem chi tiết service
    @GetMapping("/{serviceId}")
    public String viewService(
            @PathVariable String serviceId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);

        if (serviceOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Dịch vụ không tồn tại");
            return "redirect:/services";
        }

        model.addAttribute("service", serviceOpt.get());
        model.addAttribute("pageTitle",serviceOpt.get().getName().trim());
        return "admin/services/view";
    }
}
