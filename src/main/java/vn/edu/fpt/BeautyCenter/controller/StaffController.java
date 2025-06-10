package vn.edu.fpt.BeautyCenter.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.entity.Staff;
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
    public String showStaffList(Model model) {
        List<Staff> staffList = staffService.getAllStaff();
        model.addAttribute("staffList", staffList);
        model.addAttribute("newStaff", new StaffCreationRequest());
        return "admin.staffs/list";
    }

    @PostMapping("/add")
    public String addStaff(@ModelAttribute("newStaff") StaffCreationRequest staffCreationRequest,
                           Model model) {
        try {
            staffService.addStaff(staffCreationRequest);
            return "redirect:/admin/staff/";
        } catch (RuntimeException e) {
            // Trường hợp lỗi (vd: email đã tồn tại)
            model.addAttribute("staffList", staffService.getAllStaff());
            model.addAttribute("newStaff", staffCreationRequest);
            model.addAttribute("emailError", e.getMessage());
            model.addAttribute("showAddModal", true);
            return "admin.staffs/list";
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("createdAt");  // Không binding tự động
    }
    @PostMapping("/edit")
    public String updateStaff(@ModelAttribute Staff staff,
                              @RequestParam("createdAt") String createdAtStr) {
        LocalDate localDate = LocalDate.parse(createdAtStr);
        staff.setCreatedAt(localDate.atStartOfDay());

        staffService.updateStaffFromModal(staff);
        return "redirect:/admin/staff/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable String id) {
        staffService.deleteById(id);
        return "redirect:/admin/staff/";
    }
}
