package vn.edu.fpt.BeautyCenter.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class DashboardStats {
    private String mostUsedService;
    private String leastUsedService;
    private long newUsersThisWeek;
    private List<Object[]> topServices; // Object[]{name, count}
}
