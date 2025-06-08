package vn.edu.fpt.BeautyCenter.controller;
/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-8/6/2025>           <1.0>              TrungBD                      First Implement
 */

import jakarta.servlet.http.HttpSession;
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
import vn.edu.fpt.BeautyCenter.dto.request.ServiceUpdateRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.service.ServiceService;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {
    ServiceService serviceService;
    UserService userService;

    //Hiển thị toàn bộ service
    @GetMapping
    public String GetAllService(@RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                HttpSession session,
                                Model model) {
        if(isNotPermit(session)){
            return "redirect:/";
        }
        Page<ServiceResponse> services;
        if (keyword != null && !keyword.trim().isEmpty()) {
            services = serviceService.searchServices(keyword.trim(), page, size);
            model.addAttribute("keyword", keyword.trim());
        } else {
            services = serviceService.getAllServicesWithFormattedTags(page, size);
        }
        for (ServiceResponse service : services) {
            service.setCreatedBy(userService.getUserName(service.getCreatedBy()));
        }
        // Tạo map formatted tags
        Map<String, String> tagsMap = services.getContent().stream()
                .collect(Collectors.toMap(
                        ServiceResponse::getServiceId,
                        service -> formatTags(service.getTags())
                ));

        int numOfPage = services.getTotalPages();
        if (numOfPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, numOfPage)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("pageTitle", "Services List");
        model.addAttribute("services", services);
        model.addAttribute("formattedTags", tagsMap);
        return "dashboard/services/list";
    }

    // Hiển thị form add service
    @GetMapping("/add")
    public String ShowAddForm(Model model,HttpSession session) {
        if(isNotPermit(session)){
            return "redirect:/";
        }
        model.addAttribute("pageTitle", "Add New Service");
        return "admin/services/add";
    }

    // Xử lý add service
    @PostMapping("/add")
    public String save(@ModelAttribute @Valid ServiceCreationRequest request, HttpSession session) {
        if(isNotPermit(session)){
            return "redirect:/";
        }
        User user = (User)session.getAttribute("user");
        serviceService.createService(request,user.getUserId());
        return "redirect:/admin/services";
    }

    // Xem chi tiết service
    @GetMapping("/{serviceId}")
    public String viewService(
            @PathVariable String serviceId,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        if(isNotPermit(session)){
            return "redirect:/";
        }
        Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
        if (serviceOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Dịch vụ không tồn tại");
            return "redirect:/admin/services";
        }
        model.addAttribute("service", serviceOpt.get());
        model.addAttribute("pageTitle", serviceOpt.get().getName().trim());
        return "admin/services/view";
    }

    @GetMapping("/edit/{serviceId}")
    public String viewUpdateService(@PathVariable String serviceId, Model model,HttpSession session) {
        if(isNotPermit(session)){
            return "redirect:/";
        }
        Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
        String formatedTags = null;
        String time = null;
        if (serviceOpt.isPresent()) {
            List<String> tags = serviceOpt.get().getTags();
            formatedTags = tags.toString().replaceAll("^\\[|]$", "");
            time = serviceService.formatVietnameseDurationToTotalMinutes(serviceOpt.get().getDuration());
        }
        model.addAttribute("duration", time);
        model.addAttribute("tags", formatedTags);
        model.addAttribute("service", serviceOpt);
        return "admin/services/edit";
    }

    @PostMapping("/edit/{serviceId}")
    String updateService(@PathVariable String serviceId,HttpSession session, @ModelAttribute @Valid ServiceUpdateRequest request) {
        if(isNotPermit(session)){
            return "redirect:/";
        }
        serviceService.updateService(serviceId, request);
        return "redirect:/admin/services";
    }

    // Phương thức tiện ích để format tags
    private String formatTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "";
        }
        return tags.toString().replaceAll("^\\[|]$", "");
    }

    // Check Login auth
    private boolean isNotPermit(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return user == null || user.getRole() != Role.admin;
    }
}
