package vn.edu.fpt.BeautyCenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            Model model) {

        LocalDate today = LocalDate.now();

        int displayYear = (year != null) ? year : today.getYear();
        int displayWeek = (week != null) ? week : today.get(java.time.temporal.WeekFields.ISO.weekOfYear());

        // Cách tính an toàn tuyệt đối
        LocalDate firstDayOfWeek = LocalDate.of(displayYear, 1, 1)
                .plusWeeks(displayWeek - 1)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<LocalDate> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(firstDayOfWeek.plusDays(i));
        }


        List<Integer> years = Arrays.asList(2024, 2025, 2026);
        List<Integer> weeks = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            weeks.add(i);
        }

        model.addAttribute("selectedYear", displayYear);
        model.addAttribute("selectedWeek", displayWeek);
        model.addAttribute("years", years);
        model.addAttribute("weeks", weeks);
        model.addAttribute("days", days);

        // DB schedules
        List<StaffSchedule> allSchedules = scheduleService.findByDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(6));

        Map<String, List<String>> morningShifts  = new HashMap<>();
        Map<String, List<String>> afternoonShifts  = new HashMap<>();

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

        model.addAttribute("morningShifts", morningShifts);
        model.addAttribute("afternoonShifts", afternoonShifts);
        model.addAttribute("staffList", staffService.findAll());
        return "admin.staffs/schedule";
    }
    @PostMapping("/admin/staffs/schedule/add")
    public String addSchedule(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("staffIds") List<String> staffIds,
            @RequestParam("shift") String shift) {

        LocalTime start = shift.equals("MORNING") ? LocalTime.of(8, 0) : LocalTime.of(14, 0);
        LocalTime end = shift.equals("MORNING") ? LocalTime.of(14, 0) : LocalTime.of(20, 0);

        for (String staffId : staffIds) {
            StaffSchedule schedule = new StaffSchedule();
            schedule.setStaffId(staffId);
            schedule.setStartTime(LocalDateTime.of(date, start));
            schedule.setEndTime(LocalDateTime.of(date, end));
            schedule.setStatus(StaffSchedule.Status.UPCOMING);
            scheduleService.create(schedule);
        }

        return "redirect:/admin/staffs/schedule";
    }

}
