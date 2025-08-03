package vn.edu.fpt.BeautyCenter.controller;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-13   2.0      TrungBD  Enhanced with Advanced Filtering
 */

import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceFilterParams;
import vn.edu.fpt.BeautyCenter.dto.request.ServiceUpdateRequest;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.service.NotificationService;
import vn.edu.fpt.BeautyCenter.service.ServiceService;
import vn.edu.fpt.BeautyCenter.service.UserService;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Enhanced controller for managing beauty services with advanced filtering capabilities [3].
 * <p>
 * Provides comprehensive CRUD operations, advanced filtering, sorting, pagination,
 * and search functionality for beauty center services management [1].
 * Features dropdown-based filters for price range, duration, date range,
 * tags selection, and author filtering [2].
 * </p>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/services")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {

    ServiceService serviceService;
    UserService userService;
    NotificationService notificationService;

    // List of allowed sort fields to prevent SQL injection
    private static final List<String> ALLOWED_SORT_FIELDS = Arrays.asList(
            "name", "createdBy", "created_at", "duration", "price"
    );

    /**
     * Enhanced method to display services with comprehensive advanced filtering capabilities [1].
     * <p>
     * Supports multiple filter criteria including price range, duration range,
     * date range, tags selection, and author filtering, along with sorting and pagination [2].
     * Integrates seamlessly with dropdown-based filter UI components [3].
     * </p>
     *
     * @param page              current page number (default 0)
     * @param size              page size (default 5)
     * @param keyword           basic search keyword for service name and content
     * @param minPrice          minimum price filter for service cost range
     * @param maxPrice          maximum price filter for service cost range
     * @param minDuration       minimum duration filter in minutes
     * @param maxDuration       maximum duration filter in minutes
     * @param fromDate          start date for creation date range filtering
     * @param toDate            end date for creation date range filtering
     * @param selectedTags      list of selected tags for categorical filtering
     * @param createdBy         author/creator filter by user ID
     * @param sortBy            field to sort by (default "createdAt")
     * @param sortDir           sort direction: "asc" or "desc" (default "desc")
     * @param session           current HTTP session for authentication
     * @param model             model to pass attributes to the view
     * @param redirectAttributes attributes for redirect scenarios with flash messages
     * @return the view name for the service list with applied filters and data
     */
    @GetMapping({"/admin/", "/admin"})
    public String getAllServiceWithAdvancedFilter(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "minPrice", required = false) @Valid BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) @Valid BigDecimal maxPrice,
            @RequestParam(name = "minDuration", required = false) Integer minDuration,
            @RequestParam(name = "maxDuration", required = false) Integer maxDuration,
            @RequestParam(name = "fromDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(name = "toDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(name = "selectedTags", required = false) List<String> selectedTags,
            @RequestParam(name = "createdBy", required = false) String createdBy,
            @RequestParam(name = "sortBy", defaultValue = "created_at") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check if user is not permitted (not logged in or not admin)
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Please login as administrator.");
            return "redirect:/";
        }

        try {
            // Validate and sanitize input parameters
            page = Math.max(0, page); // Ensure page is not negative
            size = Math.min(Math.max(1, size), 50); // Limit size between 1-50

            // Build comprehensive filter parameters object
            ServiceFilterParams filterParams = ServiceFilterParams.builder()
                    .keyword(sanitizeKeyword(keyword))
                    .minPrice(validatePrice(minPrice))
                    .maxPrice(validatePrice(maxPrice))
                    .minDuration(validateDuration(minDuration))
                    .maxDuration(validateDuration(maxDuration))
                    .fromDate(validateDate(fromDate))
                    .toDate(validateDate(toDate))
                    .selectedTags(sanitizeTags(selectedTags))
                    .createdBy(sanitizeUserId(createdBy))
                    .build();

            // Validate price range logic
            if (filterParams.getMinPrice() != null && filterParams.getMaxPrice() != null) {
                if (filterParams.getMinPrice().compareTo(filterParams.getMaxPrice()) > 0) {
                    notificationService.addWarningMessage(redirectAttributes,
                            "Minimum price cannot be greater than maximum price. Filters adjusted.");
                    filterParams.setMinPrice(null);
                    filterParams.setMaxPrice(null);
                }
            }

            // Validate duration range logic
            if (filterParams.getMinDuration() != null && filterParams.getMaxDuration() != null) {
                if (filterParams.getMinDuration() > filterParams.getMaxDuration()) {
                    notificationService.addWarningMessage(redirectAttributes,
                            "Minimum duration cannot be greater than maximum duration. Filters adjusted.");
                    filterParams.setMinDuration(null);
                    filterParams.setMaxDuration(null);
                }
            }

            // Validate date range logic
            if (filterParams.getFromDate() != null && filterParams.getToDate() != null) {
                if (filterParams.getFromDate().isAfter(filterParams.getToDate())) {
                    notificationService.addWarningMessage(redirectAttributes,
                            "Start date cannot be after end date. Filters adjusted.");
                    filterParams.setFromDate(null);
                    filterParams.setToDate(null);
                }
            }

            // Create sort direction and validate sort field for security
            Sort.Direction direction = "asc".equalsIgnoreCase(sortDir) ?
                    Sort.Direction.ASC : Sort.Direction.DESC;
            String validatedSortBy = validateSortField(sortBy);
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, validatedSortBy));

            // Apply advanced filtering through service layer
            Page<ServiceResponse> services = serviceService.getServicesWithAdvancedFilter(
                    filterParams, pageable);

            // Enhance service data with user names for display
            for (ServiceResponse service : services) {
                try {
                    String userName = userService.getUserName(service.getCreatedBy());
                    service.setCreatedBy(userName != null ? userName : "Unknown User");
                } catch (Exception e) {
                    service.setCreatedBy("Unknown User");
                }
            }

            // Create a map of formatted tags for consistent display
            Map<String, String> tagsMap = services.getContent().stream()
                    .collect(Collectors.toMap(
                            ServiceResponse::getServiceId,
                            service -> formatTags(service.getTags()),
                            (existing, replacement) -> existing // Handle duplicate keys
                    ));

            // Generate page numbers for pagination navigation
            if (services.getTotalPages() > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, services.getTotalPages())
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            // Prepare filter-related attributes for dropdown UI
            prepareFilterAttributes(model, filterParams);

            // Prepare filter button states for active indication
            prepareFilterButtonStates(model, filterParams);
            // Add main attributes to model for view rendering
            model.addAttribute("pageTitle", "Services Management");
            model.addAttribute("services", services);
            model.addAttribute("formattedTags", tagsMap);
            model.addAttribute("filterParams", filterParams);
            model.addAttribute("sortBy", validatedSortBy);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keyword", filterParams.getKeyword());
            model.addAttribute("hasActiveFilters", filterParams.hasActiveFilters());

            // Add service statistics for dashboard insights
            addServiceStatistics(model, services);

            return "dashboard/services/list";

        } catch (Exception e) {
            // Comprehensive error handling with user-friendly messages
            String errorMessage = "Error loading services: " +
                    (e.getMessage() != null ? e.getMessage() : "Unexpected error occurred");
            notificationService.addErrorMessage(redirectAttributes, errorMessage);

            // Log error for debugging purposes
            System.err.println("Service filtering error: " + e.getMessage());

            // Return to list view with error notification
            model.addAttribute("pageTitle", "Services Management");
            model.addAttribute("services", Page.empty());
            return "dashboard/services/list";
        }
    }
    @GetMapping({"/", ""})
    public String getAllServiceWithAdvancedFilterForUser(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "minPrice", required = false) @Valid BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) @Valid BigDecimal maxPrice,
            @RequestParam(name = "minDuration", required = false) Integer minDuration,
            @RequestParam(name = "maxDuration", required = false) Integer maxDuration,
            @RequestParam(name = "fromDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(name = "toDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(name = "selectedTags", required = false) List<String> selectedTags,
            @RequestParam(name = "createdBy", required = false) String createdBy,
            @RequestParam(name = "sortBy", defaultValue = "created_at") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // Validate and sanitize input parameters
            page = Math.max(0, page); // Ensure page is not negative
            size = Math.min(Math.max(1, size), 50); // Limit size between 1-50

            // Build comprehensive filter parameters object
            ServiceFilterParams filterParams = ServiceFilterParams.builder()
                    .keyword(sanitizeKeyword(keyword))
                    .minPrice(validatePrice(minPrice))
                    .maxPrice(validatePrice(maxPrice))
                    .minDuration(validateDuration(minDuration))
                    .maxDuration(validateDuration(maxDuration))
                    .fromDate(validateDate(fromDate))
                    .toDate(validateDate(toDate))
                    .selectedTags(sanitizeTags(selectedTags))
                    .createdBy(sanitizeUserId(createdBy))
                    .build();

            // Validate price range logic
            if (filterParams.getMinPrice() != null && filterParams.getMaxPrice() != null) {
                if (filterParams.getMinPrice().compareTo(filterParams.getMaxPrice()) > 0) {
                    notificationService.addWarningMessage(redirectAttributes,
                            "Minimum price cannot be greater than maximum price. Filters adjusted.");
                    filterParams.setMinPrice(null);
                    filterParams.setMaxPrice(null);
                }
            }

            // Validate duration range logic
            if (filterParams.getMinDuration() != null && filterParams.getMaxDuration() != null) {
                if (filterParams.getMinDuration() > filterParams.getMaxDuration()) {
                    notificationService.addWarningMessage(redirectAttributes,
                            "Minimum duration cannot be greater than maximum duration. Filters adjusted.");
                    filterParams.setMinDuration(null);
                    filterParams.setMaxDuration(null);
                }
            }

            // Validate date range logic
            if (filterParams.getFromDate() != null && filterParams.getToDate() != null) {
                if (filterParams.getFromDate().isAfter(filterParams.getToDate())) {
                    notificationService.addWarningMessage(redirectAttributes,
                            "Start date cannot be after end date. Filters adjusted.");
                    filterParams.setFromDate(null);
                    filterParams.setToDate(null);
                }
            }

            // Create sort direction and validate sort field for security
            Sort.Direction direction = "asc".equalsIgnoreCase(sortDir) ?
                    Sort.Direction.ASC : Sort.Direction.DESC;
            String validatedSortBy = validateSortField(sortBy);
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, validatedSortBy));

            // Apply advanced filtering through service layer
            Page<ServiceResponse> services = serviceService.getServicesWithAdvancedFilter(
                    filterParams, pageable);

            // Enhance service data with user names for display
            for (ServiceResponse service : services) {
                try {
                    String userName = userService.getUserName(service.getCreatedBy());
                    service.setCreatedBy(userName != null ? userName : "Unknown User");
                } catch (Exception e) {
                    service.setCreatedBy("Unknown User");
                }
            }

            // Create a map of formatted tags for consistent display
            Map<String, String> tagsMap = services.getContent().stream()
                    .collect(Collectors.toMap(
                            ServiceResponse::getServiceId,
                            service -> formatTags(service.getTags()),
                            (existing, replacement) -> existing // Handle duplicate keys
                    ));

            // Generate page numbers for pagination navigation
            if (services.getTotalPages() > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, services.getTotalPages())
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            // Prepare filter-related attributes for dropdown UI
            prepareFilterAttributes(model, filterParams);

            // Prepare filter button states for active indication
            prepareFilterButtonStates(model, filterParams);
            if(session.getAttribute("user")!=null){
                model.addAttribute("isLoggedIn", true);
            }
            // Add main attributes to model for view rendering
            model.addAttribute("pageTitle", "Services Management");
            model.addAttribute("services", services);
            model.addAttribute("formattedTags", tagsMap);
            model.addAttribute("filterParams", filterParams);
            model.addAttribute("sortBy", validatedSortBy);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keyword", filterParams.getKeyword());
            model.addAttribute("hasActiveFilters", filterParams.hasActiveFilters());

            // Add service statistics for dashboard insights
            addServiceStatistics(model, services);

            return "services/list";

        } catch (Exception e) {
            // Comprehensive error handling with user-friendly messages
            String errorMessage = "Error loading services: " +
                    (e.getMessage() != null ? e.getMessage() : "Unexpected error occurred");
            notificationService.addErrorMessage(redirectAttributes, errorMessage);

            // Log error for debugging purposes
            System.err.println("Service filtering error: " + e.getMessage());

            // Return to list view with error notification
            model.addAttribute("pageTitle", "Services Management");
            model.addAttribute("services", Page.empty());
            return "dashboard/services/list";
        }
    }

    /**
     * Displays the form for adding a new service with necessary dropdown data [1].
     * <p>
     * Prepares form objects and available options for tags and other selections [2].
     * Includes validation objects for proper form binding and error handling [3].
     * </p>
     *
     * @param model   model to pass attributes to the view
     * @param session current HTTP session for authentication
     * @return the view name for the add service form
     */
    @GetMapping("/admin/add")
    public String showAddForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/";
        }

        try {
            // Add empty ServiceCreationRequest for form binding if not present
            if (!model.containsAttribute("serviceCreationRequest")) {
                model.addAttribute("serviceCreationRequest", new ServiceCreationRequest());
            }

            // Prepare dropdown data for form
            prepareFormDropdownData(model);

            model.addAttribute("pageTitle", "Add New Service");
            return "dashboard/services/add";

        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error preparing add form: " + e.getMessage());
            return "redirect:/admin";
        }
    }

    /**
     * Processes the creation of a new service with comprehensive validation [1].
     * <p>
     * Handles form validation, business logic validation, and provides
     * appropriate feedback through notification system [2].
     * Maintains form data on validation errors for better user experience [3].
     * </p>
     *
     * @param request            the service creation request with form data
     * @param bindingResult      validation results from form binding
     * @param session            current HTTP session for user identification
     * @param redirectAttributes attributes for redirect scenarios with flash messages
     * @return redirect to appropriate page based on operation result
     */
    @PostMapping("/admin/add")
    public String saveService(@ModelAttribute("serviceCreationRequest") @Valid ServiceCreationRequest request,
                              BindingResult bindingResult,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/";
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.serviceCreationRequest", bindingResult);
            redirectAttributes.addFlashAttribute("serviceCreationRequest", request);
            notificationService.addErrorMessage(redirectAttributes,
                    "Please check the information entered. All required fields must be completed correctly.");
            return "redirect:/admin/add";
        }

        try {
            // Get current user for audit trail
            User user = (User) session.getAttribute("user");
            if (user == null) {
                notificationService.addErrorMessage(redirectAttributes, "Session expired. Please login again.");
                return "redirect:/";
            }

            // Attempt to create the service
            serviceService.createService(request, user.getUserId());

            // Success notification with service name
            notificationService.addSuccessMessage(redirectAttributes,
                    "Service '" + request.getName() + "' has been successfully created!");

            return "redirect:/admin";

        } catch (AppException e) {
            // Handle business logic exceptions
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            redirectAttributes.addFlashAttribute("serviceCreationRequest", request);
            return "redirect:/admin/add";

        } catch (Exception e) {
            // Handle unexpected errors
            notificationService.addErrorMessage(redirectAttributes,
                    "An unexpected error occurred while creating the service: " + e.getMessage());
            redirectAttributes.addFlashAttribute("serviceCreationRequest", request);
            return "redirect:/admin/add";
        }
    }

    /**
     * Displays the edit form for an existing service with pre-populated data [1].
     * <p>
     * Retrieves service details, formats them for form display,
     * and prepares all necessary dropdown data for editing [2].
     * Handles cases where service doesn't exist gracefully [3].
     * </p>
     *
     * @param serviceId          the ID of the service to edit
     * @param model              model to pass attributes to the view
     * @param session            current HTTP session for authentication
     * @param redirectAttributes attributes for redirect scenarios
     * @return the view name for the edit service form
     */
    @GetMapping("/admin/edit/{serviceId}")
    public String showEditForm(@PathVariable String serviceId,
                               Model model,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/";
        }

        try {
            // Retrieve service by ID
            Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
            if (serviceOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Service not found. It may have been deleted.");
                return "redirect:/admin";
            }

            ServiceResponse service = serviceOpt.get();

            // Prepare formatted data for form display
            String formattedTags = null;
            String durationInMinutes = null;

            if (service.getTags() != null && !service.getTags().isEmpty()) {
                formattedTags = String.join(", ", service.getTags());
            }

            if (service.getDuration() != null) {
                durationInMinutes = serviceService.formatDurationToTotalMinutes(service.getDuration());
            }

            // Create and populate ServiceUpdateRequest with existing data
            ServiceUpdateRequest serviceUpdateRequest;
            if (!model.containsAttribute("serviceUpdateRequest")) {
                serviceUpdateRequest = ServiceUpdateRequest.builder()
                        .name(service.getName())
                        .content(service.getContent())
                        .duration(durationInMinutes != null ? Integer.parseInt(durationInMinutes) : null)
                        .price(BigDecimal.valueOf(service.getPrice()))
                        .tagNames(service.getTags())
                        .build();
                model.addAttribute("serviceUpdateRequest", serviceUpdateRequest);
            }

            // Prepare dropdown data for form
            prepareFormDropdownData(model);

            // Add additional attributes for display purposes
            model.addAttribute("service", service);
            model.addAttribute("tags", formattedTags);
            model.addAttribute("duration", durationInMinutes);
            model.addAttribute("pageTitle", "Edit Service: " + service.getName());

            return "dashboard/services/edit";

        } catch (NumberFormatException e) {
            notificationService.addErrorMessage(redirectAttributes, "Invalid data format in service details.");
            return "redirect:/admin";
        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error loading service for editing: " + e.getMessage());
            return "redirect:/admin";
        }
    }

    /**
     * Processes the update of an existing service with validation [1].
     * <p>
     * Handles form validation, business logic validation,
     * and provides comprehensive feedback to users [2].
     * Maintains audit trail and error handling [3].
     * </p>
     *
     * @param serviceId          the ID of the service to update
     * @param request            the service update request with form data
     * @param bindingResult      validation results from form binding
     * @param session            current HTTP session for authentication
     * @param redirectAttributes attributes for redirect scenarios
     * @return redirect to appropriate page based on operation result
     */
    @PostMapping("/admin/edit/{serviceId}")
    public String updateService(@PathVariable String serviceId,
                                @ModelAttribute("serviceUpdateRequest") @Valid ServiceUpdateRequest request,
                                BindingResult bindingResult,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Administrator privileges required.");
            return "redirect:/";
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.serviceUpdateRequest", bindingResult);
            redirectAttributes.addFlashAttribute("serviceUpdateRequest", request);
            notificationService.addErrorMessage(redirectAttributes,
                    "Please check the information entered. All fields must be valid.");
            return "redirect:/admin/edit/" + serviceId;
        }

        try {
            // Perform the update operation
            serviceService.updateService(serviceId, request);

            // Success notification with service name
            notificationService.addSuccessMessage(redirectAttributes,
                    "Service '" + request.getName() + "' has been successfully updated!");

            return "redirect:/admin";

        } catch (AppException e) {
            // Handle business logic exceptions
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            redirectAttributes.addFlashAttribute("serviceUpdateRequest", request);
            return "redirect:/admin/edit/" + serviceId;

        } catch (Exception e) {
            // Handle unexpected errors
            notificationService.addErrorMessage(redirectAttributes,
                    "An unexpected error occurred while updating the service: " + e.getMessage());
            redirectAttributes.addFlashAttribute("serviceUpdateRequest", request);
            return "redirect:/admin/edit/" + serviceId;
        }
    }

    /**
     * Displays the service details in read-only format [1].
     * <p>
     * Retrieves comprehensive service information including
     * formatted content, tags, and creation details [2].
     * Optimized for display purposes with proper formatting [3].
     * </p>
     *
     * @param serviceId          the ID of the service to view
     * @param model              model to pass attributes to the view
     * @param session            current HTTP session for authentication
     * @param redirectAttributes attributes for redirect scenarios
     * @return the view name for the service details page
     */
    @GetMapping({"/admin/{serviceId}","/{serviceId}"})
    public String viewService(@PathVariable String serviceId,
                              Model model,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        try {
            User currentUser = (User) session.getAttribute("user");
            // Retrieve service details
            Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
            if (serviceOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Service not found or has been removed.");
                return "redirect:/admin/services";
            }

            ServiceResponse service = serviceOpt.get();

            // Format creator name for display
            String creatorName = userService.getUserName(service.getCreatedBy());
            service.setCreatedBy(creatorName != null ? creatorName : "Unknown User");

            // Format tags for display
            String formattedTags = formatTags(service.getTags());

            // Add attributes for view
            model.addAttribute("service", service);
            model.addAttribute("formattedTags", formattedTags);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("isLoggedIn", currentUser != null);
            model.addAttribute("pageTitle", "Service Details: " + service.getName());

            return "services/view";

        } catch (Exception e) {
            notificationService.addErrorMessage(redirectAttributes,
                    "Error loading service details: " + e.getMessage());
            return "redirect:/admin/services";
        }
    }

    /**
     * Handles HTTP POST requests for service deletion with comprehensive security and validation.
     * <p>
     * This controller method implements the POST-Redirect-GET pattern to prevent
     * duplicate submissions and provides user feedback through flash messages.
     * It includes authentication checks, service existence validation, and proper
     * error handling with user-friendly notifications.
     * </p>
     *
     * @param serviceId the unique identifier of the service to delete
     * @param session current HTTP session for authentication and authorization
     * @param redirectAttributes Spring MVC redirect attributes for flash messages
     * @return redirect view name with appropriate success or error feedback
     */
    @GetMapping("/admin/delete/{serviceId}")
    public String deleteService(@PathVariable String serviceId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        // Security check: Verify that the current user has administrative privileges
        // Redirect to login page if user is not authenticated or lacks admin role
        if (isNotPermit(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Admin privileges required.");
            return "redirect:/";
        }

        try {
            // Validate service existence before attempting deletion
            // This prevents deletion attempts on non-existent services
            Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
            if (serviceOpt.isEmpty()) {
                // Service not found - add error message and redirect to list view
                notificationService.addErrorMessage(redirectAttributes, "Service not found.");
                return "redirect:/admin";
            }

            // Store service name for success message before deletion
            String serviceName = serviceOpt.get().getName();

            // Attempt to delete the service through the service layer
            boolean deleted = serviceService.deleteService(serviceId);

            if (deleted) {
                // Deletion successful - add success notification with service name
                notificationService.addSuccessMessage(redirectAttributes,
                        "Service '" + serviceName + "' deleted successfully");
            }

            // Redirect to services list page after successful deletion
            return "redirect:/admin";

        } catch (IllegalStateException e) {
            // Handle business rule violations (e.g., service has dependencies)
            // Redirect to delete confirmation page to show dependency information
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            return "redirect:/admin/delete/" + serviceId;

        } catch (Exception e) {
            // Handle any unexpected errors during deletion process
            // Log error and show user-friendly message while redirecting to safe location
            notificationService.addErrorMessage(redirectAttributes,
                    "Error deleting service: " + e.getMessage());
            return "redirect:/admin";
        }
    }



    // ========== HELPER METHODS ==========

    /**
     * Validates and sanitizes the sort field to prevent SQL injection [1].
     * <p>
     * Ensures only allowed fields can be used for sorting operations [2].
     * Returns default field if invalid field is provided [3].
     * </p>
     *
     * @param sortBy the field name to validate
     * @return validated field name or default "createdAt"
     */
    private String validateSortField(String sortBy) {
        if (sortBy != null && ALLOWED_SORT_FIELDS.contains(sortBy.toLowerCase())) {
            return sortBy.toLowerCase();
        }
        return "created_at"; // Default sort field
    }

    /**
     * Sanitizes and validates keyword input for search operations [1].
     * <p>
     * Prevents XSS attacks and SQL injection attempts [2].
     * Limits length and removes dangerous characters [3].
     * </p>
     *
     * @param keyword the search keyword to sanitize
     * @return sanitized keyword or null if invalid
     */
    private String sanitizeKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }

        // Remove HTML tags and limit length
        String sanitized = keyword.trim().replaceAll("<[^>]*>", "");

        // Limit length to prevent abuse
        if (sanitized.length() > 100) {
            sanitized = sanitized.substring(0, 100);
        }
        return sanitized.isEmpty() ? null : sanitized;
    }

    /**
     * Validates price input to ensure it's within acceptable range [1].
     * <p>
     * Prevents negative prices and extremely large values [2].
     * Returns null for invalid inputs [3].
     * </p>
     *
     * @param price the price value to validate
     * @return validated price or null if invalid
     */
    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null) {
            return null;
        }

        // Check for reasonable price range
        if (price.compareTo(BigDecimal.ZERO) < 0 ||
                price.compareTo(new BigDecimal("999999.99")) > 0) {
            return null;
        }

        return price;
    }

    /**
     * Validates duration input to ensure it's within service limits [1].
     * <p>
     * Ensures duration is positive and within reasonable bounds [2].
     * Returns null for invalid durations [3].
     * </p>
     *
     * @param duration the duration value to validate
     * @return validated duration or null if invalid
     */
    private Integer validateDuration(Integer duration) {
        if (duration == null) {
            return null;
        }

        // Check for reasonable duration range (1 minute to 24 hours)
        if (duration < 1 || duration > 1440) {
            return null;
        }

        return duration;
    }

    /**
     * Validates date input to ensure it's within reasonable range [1].
     * <p>
     * Prevents dates too far in the past or future [2].
     * Returns null for invalid dates [3].
     * </p>
     *
     * @param date the date value to validate
     * @return validated date or null if invalid
     */
    private LocalDate validateDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        LocalDate now = LocalDate.now();
        LocalDate minDate = now.minusYears(10); // 10 years ago
        LocalDate maxDate = now.plusYears(1);   // 1 year in future

        if (date.isBefore(minDate) || date.isAfter(maxDate)) {
            return null;
        }

        return date;
    }

    /**
     * Sanitizes and validates tag list input [1].
     * <p>
     * Removes empty tags, limits list size, and validates tag names [2].
     * Returns null for invalid or empty tag lists [3].
     * </p>
     *
     * @param tags the list of tags to sanitize
     * @return sanitized tag list or null if invalid
     */
    private List<String> sanitizeTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return null;
        }

        List<String> sanitized = tags.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(tag -> !tag.isEmpty() && tag.length() <= 50)
                .distinct()
                .limit(20) // Limit to 20 tags maximum
                .collect(Collectors.toList());

        return sanitized.isEmpty() ? null : sanitized;
    }

    /**
     * Sanitizes user ID input for filtering [1].
     * <p>
     * Validates UUID format and prevents injection attacks [2].
     * Returns null for invalid user IDs [3].
     * </p>
     *
     * @param userId the user ID to sanitize
     * @return sanitized user ID or null if invalid
     */
    private String sanitizeUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }

        String trimmed = userId.trim();

        // Basic UUID format validation (simplified)
        if (trimmed.length() == 36 && trimmed.matches("[a-fA-F0-9-]+")) {
            return trimmed;
        }

        return null;
    }

    /**
     * Prepares filter-related attributes for dropdown UI components [1].
     * <p>
     * Loads available options for all filter dropdowns [2].
     * Builds active filter descriptions for user feedback [3].
     * </p>
     *
     * @param model        model to add attributes to
     * @param filterParams current filter parameters
     */
    private void prepareFilterAttributes(Model model, ServiceFilterParams filterParams) {
        try {
            // Get available tags for filter dropdown
            List<String> availableTags = serviceService.getAllAvailableTags();
            model.addAttribute("availableTags", availableTags);

            // Get available authors for filter dropdown
            Map<String, String> availableAuthors = userService.getAllAuthorsMap();
            model.addAttribute("availableAuthors", availableAuthors);

            // Build active filters list for display
            if (filterParams.hasActiveFilters()) {
                List<String> activeFilters = buildActiveFiltersList(filterParams);
                model.addAttribute("activeFilters", activeFilters);
                model.addAttribute("activeFilterCount", activeFilters.size());
            }

        } catch (Exception e) {
            // Log error but don't break the main functionality
            System.err.println("Error preparing filter attributes: " + e.getMessage());
        }
    }

    /**
     * Prepares filter button states for active indication in UI [1].
     * <p>
     * Determines which filter categories are currently active [2].
     * Used for visual feedback in dropdown filter buttons [3].
     * </p>
     *
     * @param model        model to add attributes to
     * @param filterParams current filter parameters
     */
    private void prepareFilterButtonStates(Model model, ServiceFilterParams filterParams) {
        Map<String, Boolean> filterStates = new HashMap<>();

        filterStates.put("priceActive",
                filterParams.getMinPrice() != null || filterParams.getMaxPrice() != null);
        filterStates.put("durationActive",
                filterParams.getMinDuration() != null || filterParams.getMaxDuration() != null);
        filterStates.put("dateActive",
                filterParams.getFromDate() != null || filterParams.getToDate() != null);
        filterStates.put("tagsActive",
                filterParams.getSelectedTags() != null && !filterParams.getSelectedTags().isEmpty());
        filterStates.put("authorActive",
                filterParams.getCreatedBy() != null && !filterParams.getCreatedBy().trim().isEmpty());

        model.addAttribute("filterStates", filterStates);
    }

    /**
     * Builds a list of active filter descriptions for user display [1].
     * <p>
     * Creates human-readable descriptions of current filter settings [2].
     * Used in the active filters summary section [3].
     * </p>
     *
     * @param filterParams the current filter parameters
     * @return list of active filter descriptions
     */
    private List<String> buildActiveFiltersList(ServiceFilterParams filterParams) {
        List<String> activeFilters = new ArrayList<>();

        if (filterParams.getKeyword() != null && !filterParams.getKeyword().trim().isEmpty()) {
            activeFilters.add("Search: " + filterParams.getKeyword());
        }

        if (filterParams.getMinPrice() != null || filterParams.getMaxPrice() != null) {
            StringBuilder priceRange = new StringBuilder("Price: ");
            if (filterParams.getMinPrice() != null) {
                priceRange.append("$").append(filterParams.getMinPrice());
            }
            priceRange.append(" - ");
            if (filterParams.getMaxPrice() != null) {
                priceRange.append("$").append(filterParams.getMaxPrice());
            }
            activeFilters.add(priceRange.toString());
        }

        if (filterParams.getMinDuration() != null || filterParams.getMaxDuration() != null) {
            StringBuilder durationRange = new StringBuilder("Duration: ");
            if (filterParams.getMinDuration() != null) {
                durationRange.append(filterParams.getMinDuration()).append("min");
            }
            durationRange.append(" - ");
            if (filterParams.getMaxDuration() != null) {
                durationRange.append(filterParams.getMaxDuration()).append("min");
            }
            activeFilters.add(durationRange.toString());
        }

        if (filterParams.getFromDate() != null || filterParams.getToDate() != null) {
            StringBuilder dateRange = new StringBuilder("Date: ");
            if (filterParams.getFromDate() != null) {
                dateRange.append(filterParams.getFromDate());
            }
            dateRange.append(" - ");
            if (filterParams.getToDate() != null) {
                dateRange.append(filterParams.getToDate());
            }
            activeFilters.add(dateRange.toString());
        }

        if (filterParams.getSelectedTags() != null && !filterParams.getSelectedTags().isEmpty()) {
            activeFilters.add("Tags: " + String.join(", ", filterParams.getSelectedTags()));
        }

        if (filterParams.getCreatedBy() != null && !filterParams.getCreatedBy().trim().isEmpty()) {
            try {
                String authorName = userService.getUserName(filterParams.getCreatedBy());
                activeFilters.add("Author: " + (authorName != null ? authorName : "Unknown"));
            } catch (Exception e) {
                activeFilters.add("Author: Selected");
            }
        }

        return activeFilters;
    }

    /**
     * Prepares dropdown data needed for add/edit forms [1].
     * <p>
     * Loads all available options for form select elements [2].
     * Ensures forms have necessary data for proper rendering [3].
     * </p>
     *
     * @param model model to add dropdown data to
     */
    private void prepareFormDropdownData(Model model) {
        try {
            // Add available tags for form
            List<String> availableTags = serviceService.getAllAvailableTags();
            model.addAttribute("availableTags", availableTags);

            // Add other dropdown data as needed
            // Could include categories, staff members, etc.

        } catch (Exception e) {
            System.err.println("Error preparing form dropdown data: " + e.getMessage());
        }
    }

    /**
     * Adds service statistics for dashboard insights [1].
     * <p>
     * Calculates and provides summary statistics about services [2].
     * Enhances the admin dashboard with useful metrics [3].
     * </p>
     *
     * @param model    model to add statistics to
     * @param services current page of services
     */
    private void addServiceStatistics(Model model, Page<ServiceResponse> services) {
        try {
            Map<String, Object> statistics = new HashMap<>();

            statistics.put("totalServices", services.getTotalElements());
            statistics.put("currentPageServices", services.getNumberOfElements());
            statistics.put("totalPages", services.getTotalPages());
            statistics.put("currentPage", services.getNumber() + 1);

            // Calculate average price from current page
            if (!services.getContent().isEmpty()) {
                double avgPrice = services.getContent().stream()
                        .mapToDouble(ServiceResponse::getPrice)
                        .average()
                        .orElse(0.0);
                statistics.put("averagePrice", String.format("%.2f", avgPrice));
            } else {
                statistics.put("averagePrice", "0.00");
            }

            model.addAttribute("serviceStatistics", statistics);

        } catch (Exception e) {
            System.err.println("Error calculating service statistics: " + e.getMessage());
        }
    }

    /**
     * Formats tags list for consistent display across the application [1].
     * <p>
     * Converts tag collections to readable string format [2].
     * Handles null and empty cases gracefully [3].
     * </p>
     *
     * @param tags list of tags to format
     * @return formatted tags string
     */
    private String formatTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "No tags";
        }
        return String.join(", ", tags);
    }

    /**
     * Checks if current user has permission to access admin functions [1].
     * <p>
     * Validates session and user role for security [2].
     * Used across all controller methods for access control [3].
     * </p>
     *
     * @param session current HTTP session
     * @return true if user lacks permission, false if authorized
     */
    private boolean isNotPermit(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user == null || user.getRole() != Role.admin;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }

    private String getFirstImage(String content) {
        String img = "";
        Pattern pattern = Pattern.compile("src=\"(data:image\\/[^;]+;base64[^\"]+)\"");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            img = matcher.group(1);
        }
        return img;
    }
}
