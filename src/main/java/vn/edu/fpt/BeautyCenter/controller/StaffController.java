package vn.edu.fpt.BeautyCenter.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.BeautyCenter.entity.Staff;
import vn.edu.fpt.BeautyCenter.dto.request.StaffCreationRequest;
import vn.edu.fpt.BeautyCenter.service.StaffService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/admin/staff")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {

    StaffService staffService;

    /**
     * Danh sách tất cả nhân viên
     */
    @GetMapping("/")
    public String showStaffList(Model model) {
        List<Staff> staffList = staffService.getAllStaff();
        model.addAttribute("staffList", staffList);
        model.addAttribute("newStaff", new StaffCreationRequest());  // 👈 thêm dòng này
        return "admin.staffs/list";
    }
    @PostMapping("/add")
    public String addStaff(@ModelAttribute("newStaff") StaffCreationRequest staffCreationRequest) {
        staffService.addStaff(staffCreationRequest);
        return "redirect:/admin/staff/";
    }

    @GetMapping("/detail/{id}")
    public String showStaffDetail(@PathVariable("id") String id, Model model) {
        Staff staff = staffService.getByUserId(id);
        if (staff == null) {
            return "redirect:/admin/staff/";
        }
        model.addAttribute("staff", staff);
        return "admin.staffs/list";
    }
    @GetMapping("/edit/{id}")
    public String showEditStaffForm(@PathVariable("id") String id, Model model) {
        Staff staff = staffService.getByUserId(id);
        if (staff == null) {
            return "redirect:/admin/staff/";
        }

        StaffCreationRequest request = new StaffCreationRequest();
        request.setFullName(staff.getFullName());
        request.setEmail(staff.getEmail());
        request.setPhone(staff.getPhone());
        request.setRole(staff.getRole());
        // Không set avatar ở đây
        request.setStartDate(staff.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDate());

        model.addAttribute("staffId", id);
        model.addAttribute("editStaff", request);
        return "admin.staffs/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStaff(@PathVariable("id") String id, @ModelAttribute("editStaff") StaffCreationRequest request) {
        staffService.updateStaff(id, request);
        return "redirect:/admin/staff/";
    }
}
