package vn.edu.fpt.BeautyCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.dto.response.DashboardStats;
import vn.edu.fpt.BeautyCenter.repository.ServiceAnalyticsRepository;
import vn.edu.fpt.BeautyCenter.repository.UserRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ServiceAnalyticsRepository staffScheduleRepo;

    @Autowired
    private UserRepository userRepo;

    public DashboardStats getWeeklyStats() {
        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Chuyển sang đầu tuần (thứ 2) và cuối tuần (chủ nhật)
        LocalDate startOfWeekDate = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeekDate = today.with(DayOfWeek.SUNDAY);

        // Tạo LocalDateTime cho khoảng thời gian cần truy vấn
        LocalDateTime startOfWeek = startOfWeekDate.atStartOfDay();
        LocalDateTime endOfWeek = endOfWeekDate.atTime(LocalTime.MAX);

        // Truy vấn dịch vụ được sử dụng
        List<Object[]> usageData = staffScheduleRepo.countServiceUsageInWeek(startOfWeek, endOfWeek);

        // Truy vấn số người đăng ký mới
        long newUserCount = userRepo.countByCreatedAtBetween(startOfWeek, endOfWeek);

        // Tạo DTO kết quả
        DashboardStats stats = new DashboardStats();
        stats.setTopServices(usageData);

        if (!usageData.isEmpty()) {
            stats.setMostUsedService((String) usageData.get(0)[0]);
            stats.setLeastUsedService((String) usageData.get(usageData.size() - 1)[0]);
        } else {
            stats.setMostUsedService("Không có dữ liệu");
            stats.setLeastUsedService("Không có dữ liệu");
        }

        stats.setNewUsersThisWeek(newUserCount);
        return stats;
    }
}
