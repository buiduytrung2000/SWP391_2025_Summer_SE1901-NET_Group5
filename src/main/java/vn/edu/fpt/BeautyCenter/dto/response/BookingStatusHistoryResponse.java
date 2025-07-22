package vn.edu.fpt.BeautyCenter.dto.response;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Booking Status History Response DTO
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatusHistoryResponse {

    private String historyId;
    private String bookingId;
    private BookingStatus oldStatus;
    private BookingStatus newStatus;
    private String oldStatusDisplayName;
    private String newStatusDisplayName;
    private String changedByName;
    private String changeReason;
    private LocalDateTime createdAt;
}
