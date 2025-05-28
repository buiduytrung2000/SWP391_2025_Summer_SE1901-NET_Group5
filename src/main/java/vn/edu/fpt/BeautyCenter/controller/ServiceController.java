package vn.edu.fpt.BeautyCenter.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.Service;
import vn.edu.fpt.BeautyCenter.service.ServiceService;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {
    ServiceService serviceService;
    //Hiển thị toàn bộ service
    @GetMapping
    public String GetAllService(@RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,Model model) {
        Page<Service> services = serviceService.getAllServices(page, size);
        int numOfPage = services.getTotalPages();
        if(numOfPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, numOfPage)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("pageTitle","Services List");
        model.addAttribute("services", services);
        return "admin/services/list";
    }
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
