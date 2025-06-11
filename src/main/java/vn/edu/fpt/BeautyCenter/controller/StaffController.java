package vn.edu.fpt.BeautyCenter.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.entity.Staff;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.service.StaffService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/admin/staff")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {

    StaffService staffService;

    @GetMapping("/")
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
    public String addStaff(@ModelAttribute("newStaff") StaffCreationRequest request,
                           RedirectAttributes redirectAttributes,
                           Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
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
            model.addAttribute("showAddModal", true);
            model.addAttribute("formHasError", true);

            switch (e.getErrorCode()) {
                case STAFF_EMAIL_INVALID, STAFF_EMAIL_EXISTED -> model.addAttribute("emailError", e.getMessage());
                case STAFF_PHONE_INVALID -> model.addAttribute("phoneError", e.getMessage());
                case STAFF_GENDER_REQUIRED -> model.addAttribute("genderError", e.getMessage());
            }

            return "admin.staffs/list";
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("createdAt");  // Không binding tự động
    }
    @PostMapping("/edit")
    public String updateStaff(@ModelAttribute Staff staff,
                              @RequestParam("createdAt") String createdAtStr,RedirectAttributes redirectAttributes) {
        LocalDate localDate = LocalDate.parse(createdAtStr);
        staff.setCreatedAt(localDate.atStartOfDay());

        // Giữ role cũ từ database
        Staff existing = staffService.getByUserId(staff.getUserId());
        if (existing != null) {
            staff.setRole(existing.getRole());  // giữ nguyên role cũ
        }

        staffService.updateStaffFromModal(staff);
        redirectAttributes.addFlashAttribute("successMessage", "Staff updated successfully!");
        return "redirect:/admin/staff/";
    }
    @GetMapping("/toggle-status/{id}")
    public String toggleStatus(@PathVariable String id) {
        Staff staff = staffService.getByUserId(id);
        if (staff != null) {
            if (staff.getStatus() == null || staff.getStatus() == Staff.Status.inactive) {
                staff.setStatus(Staff.Status.active);
            } else {
                staff.setStatus(Staff.Status.inactive);
            }
            staffService.save(staff);
        }
        return "redirect:/admin/staff/";
    }


}
