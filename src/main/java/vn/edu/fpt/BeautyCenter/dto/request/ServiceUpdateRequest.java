package vn.edu.fpt.BeautyCenter.dto.request;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

/**
 * Request object for updating existing service details.
 * <p>
 * Contains validation rules and constraints for service modification operations.
 * All fields except tagNames are required and must meet specified criteria.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ServiceUpdateRequest {

    /**
     * Updated service name. Must be non-blank and under 100 characters.
     */
    @NotBlank(message = "Service name can not be blank")
    @Size(max = 100, message = "Service name must not exceed 100 characters")
    String name;

    /**
     * Revised service description/content. Cannot be empty.
     */
    @NotBlank(message = "Service content is required")
    private String content;

    /**
     * Updated duration in minutes. Must be between 1-480 minutes.
     */
    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 480, message = "Duration must not exceed 480 minutes")
    private Integer duration;

    /**
     * Updated price value. Must be between 0.01 and 9999.99.
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "9999.99", message = "Price must not exceed 9999.99")
    private BigDecimal price;

    /**
     * Modified list of tags associated with the service.
     */
    List<String> tagNames;
}
