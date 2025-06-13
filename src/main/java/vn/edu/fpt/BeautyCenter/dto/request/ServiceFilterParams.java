package vn.edu.fpt.BeautyCenter.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-13>      <1.0>              TrungBD      First Implement
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceFilterParams {

    /** Minimum price filter */
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "9999.99", message = "Price must not exceed 9999.99")
    private BigDecimal minPrice;

    /** Maximum price filter */
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "9999.99", message = "Price must not exceed 9999.99")
    private BigDecimal maxPrice;

    /** Minimum duration in minutes */
    private Integer minDuration;

    /** Maximum duration in minutes */
    private Integer maxDuration;

    /** Start date for creation date range filter */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;

    /** End date for creation date range filter */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate toDate;

    /** Selected tags for filtering */
    private List<String> selectedTags;

    /** Author/creator ID for filtering */
    private String createdBy;

    /** Basic keyword search */
    private String keyword;

    /**
     * Checks if any filter parameters are set.
     *
     * @return true if at least one filter is active, false otherwise
     */
    public boolean hasActiveFilters() {
        return minPrice != null || maxPrice != null ||
                minDuration != null || maxDuration != null ||
                fromDate != null || toDate != null ||
                (selectedTags != null && !selectedTags.isEmpty()) ||
                (createdBy != null && !createdBy.trim().isEmpty()) ||
                (keyword != null && !keyword.trim().isEmpty());
    }
}