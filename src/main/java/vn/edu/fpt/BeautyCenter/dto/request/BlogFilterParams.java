package vn.edu.fpt.BeautyCenter.dto.request;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */

import lombok.Builder;
import lombok.Data;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class BlogFilterParams {
    private String keyword;
    private BlogStatus status;
    private Integer categoryId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<String> selectedTags;
    private String authorId;
    private Boolean featured;

    public boolean hasActiveFilters() {
        return (keyword != null && !keyword.trim().isEmpty()) ||
                status != null ||
                categoryId != null ||
                fromDate != null ||
                toDate != null ||
                (selectedTags != null && !selectedTags.isEmpty()) ||
                (authorId != null && !authorId.trim().isEmpty()) ||
                (featured != null);
    }
}

