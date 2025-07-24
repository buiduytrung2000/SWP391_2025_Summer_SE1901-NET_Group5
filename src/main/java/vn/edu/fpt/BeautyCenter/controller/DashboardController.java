package vn.edu.fpt.BeautyCenter.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.fpt.BeautyCenter.dto.response.DashboardStats;
import vn.edu.fpt.BeautyCenter.repository.ServiceAnalyticsRepository;
import vn.edu.fpt.BeautyCenter.repository.UserRepository;
import vn.edu.fpt.BeautyCenter.service.DashboardService;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

@RequestMapping("/admin")
@Controller
public class DashboardController {

    @Resource
    private ServiceAnalyticsRepository analyticsRepository;

    @Resource
    private UserRepository userRepository;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/analysis")
    public String showDashboard(@RequestParam(required = false) Integer year,
                                @RequestParam(required = false) Integer week,
                                Model model) {

        LocalDate today = LocalDate.now();
        int displayYear = (year != null) ? year : today.getYear();
        int displayWeek = (week != null) ? week : today.get(WeekFields.ISO.weekOfYear());

        // Tính ngày đầu tuần
        LocalDate firstDayOfWeek = LocalDate.of(displayYear, 1, 1)
                .plusWeeks(displayWeek - 1)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);

        // Lấy dữ liệu thống kê theo khoảng tuần
        DashboardStats stats = dashboardService.getWeeklyStats(firstDayOfWeek, lastDayOfWeek);
        model.addAttribute("stats", stats);

        // Danh sách năm (dropdown)
        List<Integer> years = Arrays.asList(2024, 2025, 2026);
        model.addAttribute("years", years);

        // Danh sách tuần (dropdown)
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
        model.addAttribute("selectedYear", displayYear);
        model.addAttribute("selectedWeek", displayWeek);


        List<Map<String, Object>> products = List.of(
                Map.of("name", "Facial Cleanser", "price", 20, "sales", 1200, "change", 5, "icon", "/images/p1.png", "new", true),
                Map.of("name", "Body Lotion", "price", 35, "sales", 980, "change", -2, "icon", "/images/p2.png", "new", false)
        );
        model.addAttribute("productStats", products);
        model.addAttribute("topBlogs", dashboardService.getTopBlogs(5));

        return "admin.staffs/weekly_dashboard";
    }
}
