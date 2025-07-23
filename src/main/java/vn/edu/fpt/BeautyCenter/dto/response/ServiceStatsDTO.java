package vn.edu.fpt.BeautyCenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ServiceStatsDTO {
    private String name;
    private BigDecimal price;
    private Long usage;
    private BigDecimal totalRevenue;
}
