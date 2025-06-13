package vn.edu.fpt.BeautyCenter.controller;

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
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.service.StaffService;

import jakarta.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin/staff")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {

    StaffService staffService;

    @GetMapping({"", "/"})
    public String showStaffList(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        Page<Staff> staffPage = staffService.getStaffPage(PageRequest.of(page, size));

        model.addAttribute("staffList", staffPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", staffPage.getTotalPages());
        model.addAttribute("pageSize", size);

        model.addAttribute("newStaff", new StaffCreationRequest());
        return "admin.staffs/list";
    }

    @PostMapping("/add")
    public String addStaff(@Valid @ModelAttribute("newStaff") StaffCreationRequest request,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {

        // Xử lý lỗi validate từ annotation
        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> System.out.println("❌ " + e.getDefaultMessage()));

        }

        try {
            staffService.addStaff(request);
            redirectAttributes.addFlashAttribute("successMessage", "Staff added successfully!");
            return "redirect:/admin/staff?page=" + page + "&size=" + size;
        } catch (AppException e) {
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("createdAt"); // tránh binding không mong muốn
    }

    @PostMapping("/edit")
    public String updateStaff(@Valid @ModelAttribute StaffUpdateRequest request,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute("errorMessage", "Validation failed. Please check your input.");
            return "redirect:/admin/staff/";
        }

        try {
            staffService.updateStaffFromDto(request);
            redirectAttributes.addFlashAttribute("successMessage", "Staff updated successfully!");
        } catch (AppException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/staff/";
    }


    @GetMapping("/toggle-status/{id}")
    public String toggleStatus(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Staff staff = staffService.getByUserId(id);
        if (staff != null) {
            staff.setStatus(
                    staff.getStatus() == null || staff.getStatus() == Staff.Status.inactive
                            ? Staff.Status.active
                            : Staff.Status.inactive
            );
            staffService.save(staff);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Status updated successfully!");
        return "redirect:/admin/staff/";
    }
}
