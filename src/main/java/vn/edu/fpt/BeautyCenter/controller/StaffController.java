package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.StaffUpdateRequest;
import vn.edu.fpt.BeautyCenter.entity.Staff;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.service.StaffService;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/staff")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {

    StaffService staffService;

    /**
     * Display paginated list of staff members
     */
    @GetMapping({"", "/"})public String showStaffList(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(required = false) String keyword,
                                                      Model model,
                                                      HttpSession session) {
        Page<Staff> staffPage = keyword != null && !keyword.trim().isEmpty()
                ? staffService.searchStaff(keyword.trim(), PageRequest.of(page, size))
                : staffService.getStaffPage(PageRequest.of(page, size));

        model.addAttribute("staffList", staffPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", staffPage.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("newStaff", new StaffCreationRequest());

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null) {
            model.addAttribute("currentUserId", sessionUser.getUserId());
        }


        return "admin.staffs/list";
    }

    /**
     * Handle staff creation
     */
    @PostMapping("/add")
    public String addStaff(@Valid @ModelAttribute("newStaff") StaffCreationRequest request,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {

        // If validation fails
        if (result.hasErrors()) {
            Page<Staff> staffPage = staffService.getStaffPage(PageRequest.of(page, size));

            model.addAttribute("staffList", staffPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", staffPage.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("formHasError", true);
            model.addAttribute("showAddModal", true);
            model.addAttribute("newStaff", request);

            return "admin.staffs/list";
        }

        try {
            staffService.addStaff(request);
            redirectAttributes.addFlashAttribute("successMessage", "Staff added successfully!");
            return "redirect:/admin/staff?page=" + page + "&size=" + size;

        } catch (AppException e) {
            // Handle business logic errors, e.g., email already exists
            Page<Staff> staffPage = staffService.getStaffPage(PageRequest.of(page, size));

            model.addAttribute("staffList", staffPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", staffPage.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("newStaff", request);
            model.addAttribute("formHasError", true);
            model.addAttribute("showAddModal", true);

            if (e.getErrorCode() == ErrorCode.STAFF_EMAIL_EXISTED) {
                model.addAttribute("emailError", e.getMessage());
            }

            return "admin.staffs/list";
        }
    }

    /**
     * Prevent unwanted binding for sensitive fields (e.g., createdAt)
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("createdAt");
    }

    /**
     * Update existing staff information
     */
    @PostMapping("/edit")
    public String updateStaff(@Valid @ModelAttribute("updateStaff") StaffUpdateRequest request,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {

        if (result.hasErrors()) {
            Page<Staff> staffPage = staffService.getStaffPage(PageRequest.of(page, size));

            model.addAttribute("staffList", staffPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", staffPage.getTotalPages());
            model.addAttribute("pageSize", size);

            // 👇 Gửi lại data cần thiết để mở đúng modal
            model.addAttribute("updateStaff", request);
            model.addAttribute("editingStaffId", request.getUserId());
            model.addAttribute("formHasError", true);
            model.addAttribute("newStaff", new StaffCreationRequest());

            return "admin.staffs/list";
        }

        staffService.updateStaffFromDto(request);
        redirectAttributes.addFlashAttribute("successMessage", "Staff updated successfully!");
        return "redirect:/admin/staff?page=" + page + "&size=" + size;
    }

    /**
     * Toggle staff status (Active/Inactive)
     */
    @GetMapping("/toggle-status/{id}")
    public String toggleStatus(@PathVariable String id,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null && sessionUser.getUserId().equals(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "You cannot change your own status.");
            return "redirect:/admin/staff";
        }

        Staff staff = staffService.getByUserId(id);
        if (staff != null) {
            staff.setStatus(
                    staff.getStatus() == Staff.Status.active
                            ? Staff.Status.inactive
                            : Staff.Status.active
            );
            staffService.save(staff);
        }

        redirectAttributes.addFlashAttribute("successMessage", "Status updated successfully!");
        return "redirect:/admin/staff";
    }


}
