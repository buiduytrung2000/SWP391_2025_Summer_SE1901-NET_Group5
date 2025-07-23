package vn.edu.fpt.BeautyCenter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
    private String blogId;
    private String title;
    private String content;
    private String excerpt;
    private String thumbnailUrl;
    private String authorId;
    private String authorName;
    private Integer categoryId;
    private String categoryName;
    private String categoryColor;
    private BlogStatus status;
    private Boolean featured;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private List<String> tags;
    private List<String> imageUrls;
}
