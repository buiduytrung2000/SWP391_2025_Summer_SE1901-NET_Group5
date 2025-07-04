package vn.edu.fpt.BeautyCenter.dto.response;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BlogTagResponse {
    private Integer tagId;
    private String tagName;
    private String tagSlug;
    private Integer usageCount;
    private String popularity; // Calculated field
    private LocalDateTime createdAt;
}

