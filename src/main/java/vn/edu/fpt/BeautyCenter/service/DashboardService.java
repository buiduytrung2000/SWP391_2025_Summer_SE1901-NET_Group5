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
import java.time.DayOfWeek;
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

    public DashboardStats getWeeklyStats() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfWeek = today.with(DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime endOfWeek = today.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);

        // Truy vấn các dịch vụ đã được sử dụng (status = completed)
        List<Object[]> rawStats = appointmentRepo.countCompletedServiceStatsInWeek(
                startOfWeek, endOfWeek, Appointment.Status.completed);

        // Chuyển đổi sang DTO
        List<ServiceStatsDTO> topServices = rawStats.stream().map(row ->
                new ServiceStatsDTO(
                        (String) row[0],
                        (BigDecimal) row[1],
                        (Long) row[2],
                        (BigDecimal) row[3]
                )
        ).toList();

        // Đếm số user mới trong tuần
        long newCustomerCount = staffRepository.countByCreatedAtBetweenAndRole(
                startOfWeek,
                endOfWeek,
                "customer"
        );

        // Tạo đối tượng DashboardStats
        DashboardStats stats = new DashboardStats();
        stats.setTopServices(topServices);
        stats.setNewUsersThisWeek(newCustomerCount);

        return stats;
    }
    public List<BlogStatsDTO> getTopBlogs(int limit) {
        List<Object[]> raw = blogRepository.findTopBlogs(PageRequest.of(0, limit));

        return raw.stream().map(row ->
                new BlogStatsDTO(
                        (String) row[0],  // title
                        (String) row[1],  // url (thumbnail or full URL)
                        (String) row[2],  // author name
                        (Integer) row[3]  // view count
                )
        ).toList();
    }
}
