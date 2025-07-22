package vn.edu.fpt.BeautyCenter.dto.request;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Booking Action Request DTOs
 */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingAssignmentRequest {

    @NotBlank(message = "Staff ID is required")
    private String staffId;

    @Size(max = 500, message = "Assignment notes must not exceed 500 characters")
    private String assignmentNotes;
}

