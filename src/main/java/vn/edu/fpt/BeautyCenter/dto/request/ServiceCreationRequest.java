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
 * Request object for creating a new service.
 * <p>
 * Encapsulates all necessary information and validation rules required to create a service
 * within the Smart Beauty System.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ServiceCreationRequest {

    /**
     * Name of the service. Must not be blank and maximum 100 characters.
     */
    @NotBlank(message = "Service name can not be blank")
    @Size(max = 100, message = "Service name must not exceed 100 characters")
    String name;

    /**
     * Detailed content or description of the service. Required field.
     */
    @NotBlank(message = "Service content is required")
    String content;

    /**
     * Duration of the service in minutes. Must be between 1 and 480.
     */
    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 480, message = "Duration must not exceed 480 minutes")
    Integer duration;

    /**
     * Price of the service. Must be greater than 0 and not exceed 9999.99.
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "9999.99", message = "Price must not exceed 9999.99")
    BigDecimal price;

    /**
     * Username or ID of the user who created the service.
     */
    String createdBy;
    private String thumbnailUrl;

    /**
     * List of tag names associated with the service. No more than 10 tags,
     * tags can not be blank and no more than 50 characters
     */
    @Size(max = 10, message = "No more than 10 tags allowed")
    private List<
            @NotBlank(message = "Tag name cannot be blank")
            @Size(max = 50, message = "Tag name must not exceed 50 characters")
                    String
            > tagNames;
}
