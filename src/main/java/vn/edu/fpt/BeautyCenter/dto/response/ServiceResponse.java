package vn.edu.fpt.BeautyCenter.dto.response;
/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-8/6/2025>           <1.0>              TrungBD                      First Implement
 */
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ServiceResponse {
    String serviceId;
    String name;
    String content;
    String duration;
    Double price;
    private List<String> tags;
    private String createdBy;
    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

}

