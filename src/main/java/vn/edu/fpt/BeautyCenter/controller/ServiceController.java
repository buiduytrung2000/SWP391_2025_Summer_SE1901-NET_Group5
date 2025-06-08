package vn.edu.fpt.BeautyCenter.controller;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
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

/**
 * Controller for managing service-related operations in the admin dashboard.
 * <p>
 * Handles listing, creating, viewing, and editing services.
 * Access is restricted to users with admin role.
 * </p>
 */
@Controller
@RequestMapping("/admin/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {
    ServiceService serviceService;
    UserService userService;

    /**
     * Displays a paginated list of all services, with optional keyword search.
     *
     * @param page    current page number (default 1)
     * @param size    page size (default 5)
     * @param keyword optional search keyword
     * @param session current HTTP session for authentication
     * @param model   model to pass attributes to the view
     * @return the view name for the service list
     */
    @GetMapping
    public String GetAllService(@RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                HttpSession session,
                                Model model) {
        // Check if user is not permitted (not logged in or not admin)
        if(isNotPermit(session)){
            return "redirect:/";
        }

        // Retrieve services based on search keyword or get all services
        Page<ServiceResponse> services;
        if (keyword != null && !keyword.trim().isEmpty()) {
            services = serviceService.searchServices(keyword.trim(), page, size);
            model.addAttribute("keyword", keyword.trim());
        } else {
            services = serviceService.getAllServicesWithFormattedTags(page, size);
        }

        // Set the createdBy field for each service to the user's name
        for (ServiceResponse service : services) {
            service.setCreatedBy(userService.getUserName(service.getCreatedBy()));
        }

        // Create a map of formatted tags for display
        Map<String, String> tagsMap = services.getContent().stream()
                .collect(Collectors.toMap(
                        ServiceResponse::getServiceId,
                        service -> formatTags(service.getTags())
                ));

        // Add page numbers for pagination if needed
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

    /**
     * Shows the form to add a new service.
     *
     * @param model   model to pass attributes to the view
     * @param session current HTTP session for authentication
     * @return the view name for the add service form
     */
    @GetMapping("/add")
    public String ShowAddForm(Model model, HttpSession session) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }
        model.addAttribute("pageTitle", "Add New Service");
        return "admin/services/add";
    }

    /**
     * Handles the submission of the add service form.
     *
     * @param request the service creation request data
     * @param session current HTTP session for authentication
     * @return redirect to the service list page after successful creation
     */
    @PostMapping("/add")
    public String save(@ModelAttribute @Valid ServiceCreationRequest request, HttpSession session) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }
        // Get the current user from session and create the service
        User user = (User)session.getAttribute("user");
        serviceService.createService(request, user.getUserId());
        return "redirect:/admin/services";
    }

    /**
     * Displays details of a specific service.
     *
     * @param serviceId           the ID of the service to view
     * @param model               model to pass attributes to the view
     * @param session             current HTTP session for authentication
     * @param redirectAttributes  attributes for redirect scenarios
     * @return the view name for service details or redirect if not found
     */
    @GetMapping("/{serviceId}")
    public String viewService(
            @PathVariable String serviceId,
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }
        // Retrieve service by ID, redirect if not found
        Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
        if (serviceOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Dịch vụ không tồn tại");
            return "redirect:/admin/services";
        }
        model.addAttribute("service", serviceOpt.get());
        model.addAttribute("pageTitle", serviceOpt.get().getName().trim());
        return "admin/services/view";
    }

    /**
     * Shows the form to edit an existing service.
     *
     * @param serviceId the ID of the service to edit
     * @param model     model to pass attributes to the view
     * @param session   current HTTP session for authentication
     * @return the view name for the edit service form
     */
    @GetMapping("/edit/{serviceId}")
    public String viewUpdateService(@PathVariable String serviceId, Model model, HttpSession session) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }
        // Retrieve service by ID and prepare data for the edit form
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

    /**
     * Handles the submission of the edit service form.
     *
     * @param serviceId the ID of the service to update
     * @param session   current HTTP session for authentication
     * @param request   the service update request data
     * @return redirect to the service list page after successful update
     */
    @PostMapping("/edit/{serviceId}")
    String updateService(@PathVariable String serviceId, HttpSession session, @ModelAttribute @Valid ServiceUpdateRequest request) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }
        // Update the service with the provided data
        serviceService.updateService(serviceId, request);
        return "redirect:/admin/services";
    }

    /**
     * Utility method to format a list of tags as a comma-separated string.
     *
     * @param tags the list of tags to format
     * @return formatted string of tags, or empty string if tags are null or empty
     */
    private String formatTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "";
        }
        return tags.toString().replaceAll("^\\[|]$", "");
    }

    /**
     * Checks if the current session user is not permitted (not logged in or not admin).
     *
     * @param session current HTTP session
     * @return true if user is not permitted, false otherwise
     */
    private boolean isNotPermit(HttpSession session) {
        User user = (User)session.getAttribute("user");
        return user == null || user.getRole() != Role.admin;
    }
}
