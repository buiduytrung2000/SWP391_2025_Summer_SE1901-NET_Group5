package vn.edu.fpt.BeautyCenter.controller;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Controller
 */

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.*;
import vn.edu.fpt.BeautyCenter.dto.response.BookingResponse;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceResponse;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.service.NotificationService;
import vn.edu.fpt.BeautyCenter.service.ServiceBookingService;
import vn.edu.fpt.BeautyCenter.service.ServiceService;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ServiceBookingController {

    ServiceBookingService bookingService;
    ServiceService serviceService;
    UserService userService;
    NotificationService notificationService;

    // ========== CUSTOMER BOOKING ENDPOINTS ==========

    /**
     * Display booking form for a specific service
     */
    @GetMapping("/service/{serviceId}")
    public String showBookingForm(@PathVariable String serviceId,
                                  Model model,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                notificationService.addErrorMessage(redirectAttributes, "Please login to book a service");
                return "redirect:/admin/login";
            }

            // Get service details
            Optional<ServiceResponse> serviceOpt = serviceService.getServiceById(serviceId);
            if (serviceOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Service not found");
                return "redirect:/admin/services";
            }

            ServiceResponse service = serviceOpt.get();

            // Get available time slots for today and next few days
            LocalDate today = LocalDate.now();
            Map<LocalDate, List<LocalTime>> availableSlots = new HashMap<>();

            for (int i = 0; i < 7; i++) {
                LocalDate date = today.plusDays(i);
                List<LocalTime> slots = bookingService.getAvailableTimeSlots(serviceId, date);
                availableSlots.put(date, slots);
            }
            BookingRequest bookingRequest = new BookingRequest();
            bookingRequest.setServiceId(serviceId);
            model.addAttribute("service", service);
            model.addAttribute("bookingRequest", bookingRequest);
            model.addAttribute("availableSlots", availableSlots);
            model.addAttribute("currentUser", user);
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("pageTitle", "Book Service: " + service.getName());

            return "dashboard/services/booking";

        } catch (Exception e) {
            log.error("Error loading booking form for service {}: ", serviceId, e);
            notificationService.addErrorMessage(redirectAttributes, "Error loading booking form");
            return "redirect:/admin/services";
        }
    }

    /**
     * Process service booking
     */
    @PostMapping("/book")
    public String createBooking(@Valid @ModelAttribute BookingRequest request,
                                BindingResult bindingResult,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                notificationService.addErrorMessage(redirectAttributes, "Please login to book a service");
                return "redirect:/admin/login";
            }
            System.out.println(request);
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookingRequest", bindingResult);
                redirectAttributes.addFlashAttribute("bookingRequest", request);
                notificationService.addErrorMessage(redirectAttributes, "Please check your booking details");
                return "redirect:/admin/bookings/service/" + request.getServiceId();
            }

            BookingResponse booking = bookingService.createBooking(request, user.getUserId());

            notificationService.addSuccessMessage(redirectAttributes,
                    "Service booked successfully! Booking ID: " + booking.getBookingId());

            return "redirect:/admin/bookings/my-bookings";

        } catch (AppException e) {
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            redirectAttributes.addFlashAttribute("bookingRequest", request);
            System.out.println("app exception");
            return "redirect:/admin/bookings/service/" + request.getServiceId();
        } catch (Exception e) {
            System.err.println("Error creating booking: "+ e);
            notificationService.addErrorMessage(redirectAttributes, "Error creating booking");
            return "redirect:/admin/bookings/service/" + request.getServiceId();
        }
    }

    /**
     * Get available time slots via AJAX
     */
    @GetMapping("/available-slots/{serviceId}")
    @ResponseBody
    public ResponseEntity<?> getAvailableTimeSlots(@PathVariable String serviceId,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<LocalTime> availableSlots = bookingService.getAvailableTimeSlots(serviceId, date);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("date", date);
            response.put("availableSlots", availableSlots);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error getting available time slots: ", e);
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", "Error getting available time slots"));
        }
    }

    // ========== CUSTOMER BOOKING MANAGEMENT ==========

    /**
     * Show customer's bookings
     */
    @GetMapping("/my-bookings")
    public String showMyBookings(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                notificationService.addErrorMessage(redirectAttributes, "Please login to view your bookings");
                return "redirect:/login";
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
            Page<BookingResponse> bookingsPage = bookingService.getCustomerBookings(user.getUserId(), pageable);

            model.addAttribute("bookings", bookingsPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", bookingsPage.getTotalPages());
            model.addAttribute("totalElements", bookingsPage.getTotalElements());
            model.addAttribute("hasNext", bookingsPage.hasNext());
            model.addAttribute("hasPrevious", bookingsPage.hasPrevious());
            model.addAttribute("currentUser", user);
            model.addAttribute("pageTitle", "My Bookings");

            return "dashboard/services/my-bookings";

        } catch (Exception e) {
            System.out.println("Error loading customer bookings: "+ e);
            notificationService.addErrorMessage(redirectAttributes, "Error loading your bookings");
            return "redirect:/admin/dashboard";
        }
    }

    /**
     * Cancel customer booking
     */
    @PostMapping("/cancel/{bookingId}")
    public String cancelCustomerBooking(@PathVariable String bookingId,
                                        @RequestParam String cancellationReason,
                                        HttpSession session,
                                        RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                notificationService.addErrorMessage(redirectAttributes, "Please login to cancel booking");
                return "redirect:/admin/login";
            }

            BookingCancellationRequest request = BookingCancellationRequest.builder()
                    .cancellationReason(cancellationReason)
                    .build();

            bookingService.cancelBooking(bookingId, request, user.getUserId());

            notificationService.addSuccessMessage(redirectAttributes, "Booking cancelled successfully");
            return "redirect:/admin/bookings/my-bookings";

        } catch (AppException e) {
            notificationService.addErrorMessage(redirectAttributes, e.getMessage());
            return "redirect:/admin/bookings/my-bookings";
        } catch (Exception e) {
            log.error("Error cancelling booking: ", e);
            notificationService.addErrorMessage(redirectAttributes, "Error cancelling booking");
            return "redirect:/admin/bookings/my-bookings";
        }
    }

    // ========== ADMIN BOOKING MANAGEMENT ==========

    /**
     * Show all bookings for admin
     */
    @GetMapping("/admin")
    public String showAdminBookings(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) BookingStatus status,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            notificationService.addErrorMessage(redirectAttributes, "Access denied. Admin privileges required.");
            return "redirect:/";
        }

        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
            Page<BookingResponse> bookingsPage;

            if (status != null) {
                bookingsPage = bookingService.getBookingsByStatus(status, pageable);
            } else {
                bookingsPage = bookingService.getAllBookings(pageable);
            }

            // Get pending bookings count
            long pendingCount = bookingService.getBookingCountByStatus(BookingStatus.PENDING);

            model.addAttribute("bookings", bookingsPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", bookingsPage.getTotalPages());
            model.addAttribute("totalElements", bookingsPage.getTotalElements());
            model.addAttribute("hasNext", bookingsPage.hasNext());
            model.addAttribute("hasPrevious", bookingsPage.hasPrevious());
            model.addAttribute("selectedStatus", status);
            model.addAttribute("pendingCount", pendingCount);
            model.addAttribute("bookingStatuses", BookingStatus.values());
            model.addAttribute("pageTitle", "Booking Management");

            return "dashboard/services/admin-bookings";

        } catch (Exception e) {
            log.error("Error loading admin bookings: ", e);
            notificationService.addErrorMessage(redirectAttributes, "Error loading bookings");
            return "redirect:/admin/dashboard";
        }
    }
    /**
     * Lấy danh sách nhân viên khả dụng cho một booking.
     * Trả về các staff có thể nhận lịch vào ngày và giờ đã đặt.
     */
    @GetMapping("/available-staff/{bookingId}")
    public ResponseEntity<?> getAvailableStaff(
            @PathVariable String bookingId,
            HttpSession session) {
        log.info("🔍 Available staff request for booking: {}", bookingId);
        try {
            User admin = (User) session.getAttribute("user");
            if (admin == null) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Admin login required"));
            }
            // bookingService sẽ return List<StaffSummaryResponse>
            List<User> staffList = bookingService.getAvailableStaffForBooking(bookingId);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "staff", staffList
            ));
        } catch (AppException e) {
            log.warn("❌ AppException in getAvailableStaff: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            log.error("❌ Unexpected error in getAvailableStaff", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error retrieving available staff"));
        }
    }

    /**
     * Assign booking to staff
     */
    @PostMapping("/assign/{bookingId}")
    @ResponseBody
    public ResponseEntity<?> assignBookingToStaff(@PathVariable String bookingId,
                                                  @Valid @RequestBody BookingAssignmentRequest request,
                                                  HttpSession session) {
        try {
            User admin = (User) session.getAttribute("user");
            if (admin == null || admin.getRole() != Role.admin) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Admin access required"));
            }

            BookingResponse booking = bookingService.assignBookingToStaff(bookingId, request, admin.getUserId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Booking assigned successfully");
            response.put("booking", booking);

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            log.error("Error assigning booking: ", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error assigning booking"));
        }
    }

    // ========== STAFF BOOKING MANAGEMENT ==========

    /**
     * Show staff's assigned bookings
     */
    @GetMapping("/staff")
    public String showStaffBookings(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        try {
            User staff = (User) session.getAttribute("user");
            if (staff == null || staff.getRole() != Role.staff) {
                notificationService.addErrorMessage(redirectAttributes, "Staff access required");
                return "redirect:/admin/dashboard";
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "bookingDate", "bookingTime"));
            Page<BookingResponse> bookingsPage = bookingService.getStaffBookings(staff.getUserId(), pageable);

            // Get active bookings for today
            List<BookingResponse> todayBookings = bookingService.getTodayBookings()
                    .stream()
                    .filter(booking -> staff.getUserId().equals(booking.getStaffId()))
                    .toList();

            model.addAttribute("bookings", bookingsPage.getContent());
            model.addAttribute("todayBookings", todayBookings);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", bookingsPage.getTotalPages());
            model.addAttribute("totalElements", bookingsPage.getTotalElements());
            model.addAttribute("hasNext", bookingsPage.hasNext());
            model.addAttribute("hasPrevious", bookingsPage.hasPrevious());
            model.addAttribute("currentUser", staff);
            model.addAttribute("pageTitle", "My Assigned Services");

            return "dashboard/services/staff-bookings";

        } catch (Exception e) {
            log.error("Error loading staff bookings: ", e);
            notificationService.addErrorMessage(redirectAttributes, "Error loading your assigned services");
            return "redirect:/admin/dashboard";
        }
    }

    /**
     * Start service
     */
    @PostMapping("/staff/start/{bookingId}")
    @ResponseBody
    public ResponseEntity<?> startService(@PathVariable String bookingId,
                                          HttpSession session) {
        try {
            User staff = (User) session.getAttribute("user");
            if (staff == null || staff.getRole() != Role.staff) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Staff access required"));
            }

            BookingResponse booking = bookingService.startBooking(bookingId, staff.getUserId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Service started successfully");
            response.put("booking", booking);

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            log.error("Error starting service: ", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error starting service"));
        }
    }

    /**
     * Complete service
     */
    @PostMapping("/staff/complete/{bookingId}")
    @ResponseBody
    public ResponseEntity<?> completeService(@PathVariable String bookingId,
                                             @Valid BookingCompletionRequest request,
                                             HttpSession session) {
        System.out.println("complete controller");
        try {
            User staff = (User) session.getAttribute("user");
            if (staff == null || staff.getRole() != Role.staff) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Staff access required"));
            }

            BookingResponse booking = bookingService.completeBooking(bookingId, request, staff.getUserId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Service completed successfully");
            response.put("booking", booking);

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            log.error("Error completing service: ", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error completing service"));
        }
    }

    /**
     * Cancel service with reason
     */
    @PostMapping("/staff/cancel/{bookingId}")
    @ResponseBody
    public ResponseEntity<?> cancelService(
            @PathVariable String bookingId,
            @Valid @RequestBody BookingCancellationRequest request,
            HttpSession session) {
        try {
            User staff = (User) session.getAttribute("user");
            if (staff == null || staff.getRole() != Role.staff) {
                return ResponseEntity.status(401)
                        .body(Map.of("success", false, "message", "Staff access required"));
            }
            // request.getCancellationReason() now populated
            BookingResponse booking = bookingService.cancelBooking(
                    bookingId, request, staff.getUserId());

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Service cancelled successfully",
                    "booking", booking
            ));
        } catch (AppException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            log.error("Error cancelling service: ", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "message", "Error cancelling service"));
        }
    }

    // ========== UTILITY METHODS ==========

    /**
     * Get booking details
     */
    @GetMapping("/{bookingId}")
    public String viewBookingDetails(@PathVariable String bookingId,
                                     Model model,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                notificationService.addErrorMessage(redirectAttributes, "Please login to view booking details");
                return "redirect:/admin/login";
            }

            Optional<BookingResponse> bookingOpt = bookingService.getBookingById(bookingId);
            if (bookingOpt.isEmpty()) {
                notificationService.addErrorMessage(redirectAttributes, "Booking not found");
                return "redirect:/admin/bookings/my-bookings";
            }

            BookingResponse booking = bookingOpt.get();

            // Check access permissions
            if (!bookingService.canUserAccessBooking(bookingId, user.getUserId()) && user.getRole() != Role.admin) {
                notificationService.addErrorMessage(redirectAttributes, "Access denied");
                return "redirect:/admin/bookings/my-bookings";
            }

            model.addAttribute("booking", booking);
            model.addAttribute("currentUser", user);
            model.addAttribute("pageTitle", "Booking Details");

            return "bookings/booking-details";

        } catch (Exception e) {
            log.error("Error loading booking details: ", e);
            notificationService.addErrorMessage(redirectAttributes, "Error loading booking details");
            return "redirect:/admin/bookings/my-bookings";
        }
    }

    // Helper methods
    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null && user.getRole() == Role.admin;
    }
}
