package vn.edu.fpt.BeautyCenter.dto.response;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object representing the response for a service entity.
 * <p>
 * Encapsulates service details sent to clients, including identifiers, descriptive fields,
 * pricing, tags, and metadata such as creator and creation time.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ServiceResponse {

    /** Unique identifier for the service. */
    String serviceId;

    /** Name of the service. */
    String name;

    /** Description or content of the service. */
    String content;

    /** Duration of the service, typically in minutes (as a string for formatting flexibility). */
    String duration;

    /** Price of the service. */
    Double price;

    /** List of tags associated with the service. */
    private List<String> tags;

    /** Username or ID of the user who created the service. */
    private String createdBy;

    /** Timestamp when the service was created. */
    private LocalDateTime createdAt;

    // /** Timestamp when the service was last updated. */
    // private LocalDateTime updatedAt;
}
