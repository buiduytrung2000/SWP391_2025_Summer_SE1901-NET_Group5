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

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Generic API response wrapper for RESTful endpoints.
 * <p>
 * Encapsulates a standard response structure with a status code, message, and result payload.
 * Fields with null values are excluded from JSON serialization.
 * </p>
 *
 * @param <T> the type of the result payload
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    /** Status code representing the result of the API call (default: 1000). */
    int code = 1000;

    /** Human-readable message describing the API response. */
    String message;

    /** The result payload of the API response. */
    T result;
}
