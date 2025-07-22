package vn.edu.fpt.BeautyCenter.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class DashboardStats {

    private long newUsersThisWeek;
    private long totalVisitors;
    private List<ServiceStatsDTO> topServices;

}
