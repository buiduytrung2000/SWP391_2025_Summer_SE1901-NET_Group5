package vn.edu.fpt.BeautyCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.fpt.BeautyCenter.entity.StaffSchedule;
import vn.edu.fpt.BeautyCenter.service.StaffScheduleService;
import vn.edu.fpt.BeautyCenter.service.StaffService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class StaffScheduleViewController {

    private final StaffScheduleService scheduleService;
    private final StaffService staffService;

    @GetMapping("/admin/staffs/schedule")
    public String getSchedule(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer week,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            Model model) {

        LocalDate today = LocalDate.now();

        // Nếu không chọn date -> mặc định là hôm nay
        if (selectedDate == null) {
            selectedDate = today;
        }

        // Xử lý hiển thị tuần
        int displayYear = (year != null) ? year : today.getYear();
        int displayWeek = (week != null) ? week : today.get(java.time.temporal.WeekFields.ISO.weekOfYear());

        LocalDate firstDayOfWeek = LocalDate.of(displayYear, 1, 1)
                .plusWeeks(displayWeek - 1)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<LocalDate> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(firstDayOfWeek.plusDays(i));
        }

        // Dropdown
        List<Integer> years = Arrays.asList(2024, 2025, 2026);
        List<Map<String, String>> weeks = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            LocalDate weekFirstDay = LocalDate.of(displayYear, 1, 1)
                    .plusWeeks(i - 1)
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate weekLastDay = weekFirstDay.plusDays(6);
            String label = String.format("%02d/%02d - %02d/%02d",
                    weekFirstDay.getDayOfMonth(), weekFirstDay.getMonthValue(),
                    weekLastDay.getDayOfMonth(), weekLastDay.getMonthValue());
            Map<String, String> weekMap = new HashMap<>();
            weekMap.put("number", String.valueOf(i));
            weekMap.put("label", label);
            weeks.add(weekMap);
        }
        model.addAttribute("weeks", weeks);

        // DB schedules
        List<StaffSchedule> allSchedules = scheduleService.findByDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(6));

        Map<String, List<String>> morningShifts = new HashMap<>();
        Map<String, List<String>> afternoonShifts = new HashMap<>();

        for (LocalDate day : days) {
            List<String> morning = new ArrayList<>();
            List<String> afternoon = new ArrayList<>();
            for (StaffSchedule sc : allSchedules) {
                if (sc.getStartTime().toLocalDate().equals(day)) {
                    String info = sc.getStaff().getFullName()
                            + " (" + sc.getStaff().getPosition() + ") - "
                            + sc.getStatus().name();
                    if (sc.getStartTime().getHour() < 14) {
                        morning.add(info);
                    } else {
                        afternoon.add(info);
                    }
                }
            }
            morningShifts.put(day.toString(), morning);
            afternoonShifts.put(day.toString(), afternoon);
        }

        // Lấy lịch của ngày selectedDate để dùng cho modal attendance
        List<StaffSchedule> todaySchedules = scheduleService.findByDate(selectedDate);

        model.addAttribute("selectedYear", displayYear);
        model.addAttribute("selectedWeek", displayWeek);
        model.addAttribute("years", years);
        model.addAttribute("weeks", weeks);
        model.addAttribute("days", days);

        model.addAttribute("morningShifts", morningShifts);
        model.addAttribute("afternoonShifts", afternoonShifts);
        model.addAttribute("staffList", staffService.findAll());

        // cho modal attendance
        model.addAttribute("selectedDate", selectedDate);
        model.addAttribute("todaySchedules", todaySchedules);

        return "admin.staffs/schedule";
    }

    @PostMapping("/admin/staffs/schedule/add")
    public String addSchedule(RedirectAttributes redirectAttributes,
                              @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              @RequestParam("staffIds") List<String> staffIds,
                              @RequestParam("shift") String shift) {
        LocalTime start = shift.equals("MORNING") ? LocalTime.of(8, 0) : LocalTime.of(14, 0);
        LocalTime end = shift.equals("MORNING") ? LocalTime.of(14, 0) : LocalTime.of(20, 0);

        for (String staffId : staffIds) {
            if (scheduleService.existsSchedule(staffId, date, shift)) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Staff already has a schedule on " + date + " for " + shift + " shift.");
                return "redirect:/admin/staffs/schedule";
            }
            StaffSchedule schedule = new StaffSchedule();
            schedule.setStaffId(staffId);
            schedule.setStartTime(LocalDateTime.of(date, start));
            schedule.setEndTime(LocalDateTime.of(date, end));
            schedule.setStatus(StaffSchedule.Status.UPCOMING);
            scheduleService.create(schedule);
        }
        redirectAttributes.addFlashAttribute("successMessageAddStaff", "Added schedule successfully!");
        return "redirect:/admin/staffs/schedule";
    }

    @PostMapping("/admin/staffs/schedule/update-attendance")
    public String updateAttendance(@RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {
        params.forEach((key, value) -> {
            if (key.startsWith("status-")) {
                String scheduleId = key.substring(7);
                StaffSchedule schedule = scheduleService.findById(scheduleId);
                if ("ATTENDANCE".equals(value)) {
                    schedule.setStatus(StaffSchedule.Status.ATTENDANCE);
                } else if ("ABSENT".equals(value)) {
                    schedule.setStatus(StaffSchedule.Status.ABSENT);
                }
                scheduleService.create(schedule); // save lại
            }
        });
        redirectAttributes.addFlashAttribute("successMessageAttendance", "Updated attendance successfully!");
        return "redirect:/admin/staffs/schedule";
    }

    @GetMapping("/admin/staffs/schedule/delete/{id}")
    public String deleteSchedule(@PathVariable String id, RedirectAttributes redirectAttributes) {
        scheduleService.delete(id);
        redirectAttributes.addFlashAttribute("successMessageAttendance", "Deleted schedule successfully!");
        return "redirect:/admin/staffs/schedule";
    }
}
