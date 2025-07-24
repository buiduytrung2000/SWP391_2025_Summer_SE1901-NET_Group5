package vn.edu.fpt.BeautyCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.dto.response.BlogStatsDTO;
import vn.edu.fpt.BeautyCenter.dto.response.DashboardStats;
import vn.edu.fpt.BeautyCenter.dto.response.ServiceStatsDTO;
import vn.edu.fpt.BeautyCenter.entity.Appointment;
import vn.edu.fpt.BeautyCenter.repository.BlogRepository;
import vn.edu.fpt.BeautyCenter.repository.ServiceAnalyticsRepository;
import vn.edu.fpt.BeautyCenter.repository.StaffRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ServiceAnalyticsRepository appointmentRepo;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private BlogRepository blogRepository;

    /**
     * Hàm mới: Lấy thống kê theo khoảng tuần
     */
    public DashboardStats getWeeklyStats(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startOfWeek = startDate.atStartOfDay();
        LocalDateTime endOfWeek = endDate.atTime(LocalTime.MAX);

        // Truy vấn các dịch vụ đã được sử dụng trong tuần (chỉ tính completed)
        List<Object[]> rawStats = appointmentRepo.countCompletedServiceStatsInWeek(
                startOfWeek, endOfWeek, Appointment.Status.completed);

        List<ServiceStatsDTO> topServices = rawStats.stream().map(row ->
                new ServiceStatsDTO(
                        (String) row[0],      // service name
                        (BigDecimal) row[1],  // price
                        (Long) row[2],        // usage count
                        (BigDecimal) row[3]   // total revenue
                )
        ).toList();

        // Đếm số user mới (role = 'customer') trong tuần
        long newCustomerCount = staffRepository.countByCreatedAtBetweenAndRole(
                startOfWeek,
                endOfWeek,
                "customer"
        );

        DashboardStats stats = new DashboardStats();
        stats.setTopServices(topServices);
        stats.setNewUsersThisWeek(newCustomerCount);
        return stats;
    }

    /**
     * Hàm cũ: Lấy thống kê của tuần hiện tại (nếu cần dùng lại)
     */
    public DashboardStats getWeeklyStats() {
        LocalDate today = LocalDate.now();
        return getWeeklyStats(
                today.with(java.time.DayOfWeek.MONDAY),
                today.with(java.time.DayOfWeek.SUNDAY)
        );
    }

    public List<BlogStatsDTO> getTopBlogs(int limit) {
        List<Object[]> raw = blogRepository.findTopBlogs(PageRequest.of(0, limit));
        return raw.stream().map(row ->
                new BlogStatsDTO(
                        (String) row[0],  // title
                        (String) row[1],  // url
                        (String) row[2],  // author
                        (Integer) row[3]  // view count
                )
        ).toList();
    }
}
