
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceUpdateRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.service.NotificationService;
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
 * Handles listing, creating, viewing, and editing services with comprehensive
 * error handling and user notifications. Access is restricted to users with admin role.
 * </p>
 */
@Controller
@RequestMapping("/admin/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {
    ServiceService serviceService;
    UserService userService;
    NotificationService notificationService;

    /**
     * Displays a paginated list of all services, with optional keyword search.
     *
     * @param page    current page number (default 0)
     * @param size    page size (default 5)
     * @param keyword optional search keyword
     * @param session current HTTP session for authentication
     * @param model   model to pass attributes to the view
     * @return the view name for the service list
     */
    @GetMapping({"","/"})
    public String getAllService(@RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                HttpSession session,
                                Model model) {
        // Check if user is not permitted (not logged in or not admin)
        if(isNotPermit(session)){
            return "redirect:/";
        }

        try {
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
        } catch (Exception e) {
            // Log error and redirect with error message
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải danh sách dịch vụ: " + e.getMessage());
            return "dashboard/services/list";
        }
    }

    /**
     * Shows the form to add a new service.
     *
     * @param model   model to pass attributes to the view
     * @param session current HTTP session for authentication
     * @return the view name for the add service form
     */
    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }

        // Add empty ServiceCreationRequest for form binding
        if (!model.containsAttribute("serviceCreationRequest")) {
            model.addAttribute("serviceCreationRequest", new ServiceCreationRequest());
        }

        model.addAttribute("pageTitle", "Add New Service");
        return "admin/services/add";
    }

    /**
     * Handles the submission of the add service form with validation.
     *
     * @param request             the service creation request data
     * @param bindingResult       validation results
     * @param session             current HTTP session for authentication
     * @param redirectAttributes  attributes for redirect scenarios
     * @return redirect to appropriate page based on validation results
     */
    @PostMapping("/add")
    public String save(@ModelAttribute("serviceCreationRequest") @Valid ServiceCreationRequest request,
                       BindingResult bindingResult,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            // Preserve the form data and validation errors
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.serviceCreationRequest", bindingResult);
            redirectAttributes.addFlashAttribute("serviceCreationRequest", request);
            notificationService.addErrorMessage(redirectAttributes, "Vui lòng kiểm tra lại thông tin đã nhập!");
            ;
            return "redirect:/admin/services/add";
        }

        try {
            // Get the current user from session and create the service
            User user = (User)session.getAttribute("user");
            serviceService.createService(request, user.getUserId());

            // Add success message
            notificationService.addSuccessMessage(redirectAttributes, "Thêm dịch vụ thành công!");

            return "redirect:/admin/services";
        } catch (Exception e) {
            // Handle service creation errors
            notificationService.addErrorMessage(redirectAttributes, "Có lỗi xảy ra khi thêm dịch vụ: " + e.getMessage());

            // Preserve form data for user to correct
            redirectAttributes.addFlashAttribute("serviceCreationRequest", request);
            return "redirect:/admin/services/add";
        }
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
    public String viewService(@PathVariable String serviceId,
                              Model model,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }

        try {
            // Retrieve service by ID
            Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
            if (serviceOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Dịch vụ không tồn tại!");
                return "redirect:/admin/services";
            }

            model.addAttribute("service", serviceOpt.get());
            model.addAttribute("pageTitle", serviceOpt.get().getName().trim());
            return "admin/services/view";
        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes, "Có lỗi xảy ra khi tải thông tin dịch vụ: " + e.getMessage());
            return "redirect:/admin/services";
        }
    }

    /**
     * Shows the form to edit an existing service.
     *
     * @param serviceId the ID of the service to edit
     * @param model     model to pass attributes to the view
     * @param session   current HTTP session for authentication
     * @param redirectAttributes attributes for redirect scenarios
     * @return the view name for the edit service form
     */
    @GetMapping("/edit/{serviceId}")
    public String viewUpdateService(@PathVariable String serviceId,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }

        try {
            // Retrieve service by ID and prepare data for the edit form
            Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
            if (serviceOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Dịch vụ không tồn tại!");
                return "redirect:/admin/services";
            }

            String formattedTags = null;
            String time = null;
            List<String> tags = serviceOpt.get().getTags();
            formattedTags = tags.toString().replaceAll("^\\[|]$", "");
            time = serviceService.formatDurationToTotalMinutes(serviceOpt.get().getDuration());

            // Add empty ServiceUpdateRequest for form binding if not present
            if (!model.containsAttribute("serviceUpdateRequest")) {
                model.addAttribute("serviceUpdateRequest", new ServiceUpdateRequest());
            }

            model.addAttribute("duration", time);
            model.addAttribute("tags", formattedTags);
            model.addAttribute("service", serviceOpt.get());
            model.addAttribute("pageTitle", "Edit Service: " + serviceOpt.get().getName());

            return "admin/services/edit";
        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes, "Có lỗi xảy ra khi tải thông tin dịch vụ: " + e.getMessage());
            return "redirect:/admin/services";
        }
    }

    /**
     * Handles the submission of the edit service form with validation.
     *
     * @param serviceId           the ID of the service to update
     * @param session             current HTTP session for authentication
     * @param request             the service update request data
     * @param bindingResult       validation results
     * @param redirectAttributes  attributes for redirect scenarios
     * @return redirect to appropriate page based on validation results
     */
    @PostMapping("/edit/{serviceId}")
    public String updateService(@PathVariable String serviceId,
                                HttpSession session,
                                @ModelAttribute("serviceUpdateRequest") @Valid ServiceUpdateRequest request,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        // Check if user is not permitted
        if(isNotPermit(session)){
            return "redirect:/";
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            // Preserve the form data and validation errors
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.serviceUpdateRequest", bindingResult);
            redirectAttributes.addFlashAttribute("serviceUpdateRequest", request);
            notificationService.addErrorMessage(redirectAttributes, "Vui lòng kiểm tra lại thông tin đã nhập!");
            return "redirect:/admin/services/edit/" + serviceId;
        }

        try {
            // Update the service with the provided data
            serviceService.updateService(serviceId, request);

            // Add success message
            notificationService.addSuccessMessage(redirectAttributes, "Cập nhật dịch vụ thành công!");

            return "redirect:/admin/services";
        } catch (Exception e) {
            // Handle service update errors
            notificationService.addErrorMessage(redirectAttributes, "Có lỗi xảy ra khi cập nhật dịch vụ: " + e.getMessage());

            // Preserve form data for user to correct
            redirectAttributes.addFlashAttribute("serviceUpdateRequest", request);
            return "redirect:/admin/services/edit/" + serviceId;
        }
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
