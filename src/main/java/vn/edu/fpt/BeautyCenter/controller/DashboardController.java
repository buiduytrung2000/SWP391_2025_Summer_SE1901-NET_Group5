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
import vn.edu.fpt.BeautyCenter.service.WebVisitService;

import java.time.*;
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

    @Autowired
    private WebVisitService webVisitService;

    @GetMapping("/analysis")
    public String showDashboard(Model model) {

        long weeklyVisitors = webVisitService.getWeeklyVisitorCount();
        DashboardStats stats = dashboardService.getWeeklyStats();
        stats.setTotalVisitors(weeklyVisitors);
        model.addAttribute("stats", stats);
        List<String> serviceNames = new ArrayList<>();
        List<Long> serviceCounts = new ArrayList<>();


        List<Map<String, Object>> products = List.of(
                Map.of("name", "Facial Cleanser", "price", 20, "sales", 1200, "change", 5, "icon", "/images/p1.png", "new", true),
                Map.of("name", "Body Lotion", "price", 35, "sales", 980, "change", -2, "icon", "/images/p2.png", "new", false)
        );

        Map<String, Object> visitors = new HashMap<>();
        visitors.put("thisWeek", List.of(100, 120, 160, 155, 165, 170, 160));
        visitors.put("lastWeek", List.of(60, 70, 68, 65, 67, 70, 85));
        visitors.put("labels", List.of("18th", "20th", "22nd", "24th", "26th", "28th", "30th"));

        model.addAttribute("topServiceNames", serviceNames);
        model.addAttribute("topServiceCounts", serviceCounts);
        model.addAttribute("productStats", products);
        model.addAttribute("visitors", visitors);
        model.addAttribute("topBlogs", dashboardService.getTopBlogs(5));
        return "admin.staffs/weekly_dashboard";
    }
}