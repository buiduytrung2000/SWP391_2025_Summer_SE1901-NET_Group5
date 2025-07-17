package vn.edu.fpt.BeautyCenter.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StaffScheduleDto {
    private String scheduleId;
    private String staffName;
    private LocalDateTime startTime;
    private String status;
    private String room;
    private String service;

}
