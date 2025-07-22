package vn.edu.fpt.BeautyCenter.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      First Implement
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingCompletionRequest {

    @Size(max = 500, message = "Completion notes must not exceed 500 characters")
    private String completionNotes;
}
