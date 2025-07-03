package vn.edu.fpt.BeautyCenter.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @Size(max = 500, message = "Excerpt must not exceed 500 characters")
    private String excerpt;

    private String thumbnailUrl;

    private Integer categoryId;

    @NotNull(message = "Status is required")
    private BlogStatus status;

    private Boolean featured = false;

    private List<String> tagNames;
}
