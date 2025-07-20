package vn.edu.fpt.BeautyCenter.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.BeautyCenter.dto.response.DashboardStats;
import vn.edu.fpt.BeautyCenter.repository.ServiceAnalyticsRepository;
import vn.edu.fpt.BeautyCenter.repository.UserRepository;
import vn.edu.fpt.BeautyCenter.service.DashboardService;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

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
    public String showDashboard(Model model) {
        DashboardStats stats = dashboardService.getWeeklyStats();
        LocalDate today = LocalDate.now();
        LocalDate startOfWeekDate = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeekDate = today.with(DayOfWeek.SUNDAY);

        // ✅ Sửa tại đây: chuyển sang LocalDateTime
        LocalDateTime startOfWeek = startOfWeekDate.atStartOfDay();
        LocalDateTime endOfWeek = endOfWeekDate.atTime(LocalTime.MAX);

        // ✅ Truyền LocalDateTime thay vì LocalDate
        List<Object[]> topServices = analyticsRepository.countServiceUsageInWeek(startOfWeek, endOfWeek);

        List<String> serviceNames = new ArrayList<>();
        List<Long> serviceCounts = new ArrayList<>();

        for (Object[] row : topServices) {
            serviceNames.add((String) row[0]);
            serviceCounts.add((Long) row[1]);
        }

        String mostUsed = serviceNames.isEmpty() ? "Không có dữ liệu" : serviceNames.get(0);
        String leastUsed = serviceNames.size() <= 1 ? "Không có dữ liệu" : serviceNames.get(serviceNames.size() - 1);

        long newUsers = userRepository.countByCreatedAtBetween(startOfWeek, endOfWeek);

        model.addAttribute("topServiceNames", serviceNames);
        model.addAttribute("topServiceCounts", serviceCounts);
        model.addAttribute("mostUsedService", mostUsed);
        model.addAttribute("leastUsedService", leastUsed);
        model.addAttribute("newUsersThisWeek", newUsers);
        model.addAttribute("stats", stats);
        return "admin.staffs/weekly_dashboard";
    }

}
