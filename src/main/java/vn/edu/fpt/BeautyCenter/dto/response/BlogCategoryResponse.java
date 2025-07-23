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
public class BlogCategoryResponse {
    private Integer categoryId;
    private String categoryName;
    private String categorySlug;
    private String description;
    private String colorCode;
    private LocalDateTime createdAt;
    private Long blogCount; // Số lượng blog thuộc category này
}
