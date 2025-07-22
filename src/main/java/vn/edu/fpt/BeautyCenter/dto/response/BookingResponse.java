package vn.edu.fpt.BeautyCenter.dto.response;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Response DTO
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private String bookingId;

    // Service information
    private String serviceId;
    private String serviceName;
    private String serviceDescription;
    private BigDecimal servicePrice;
    private String serviceDuration;

    // Customer information
    private String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    // Staff information
    private String staffId;
    private String staffName;
    private String staffEmail;

    // Booking details
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private BookingStatus status;
    private String statusDisplayName;
    private String notes;
    private String cancellationReason;
    private BigDecimal totalPrice;

    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime assignedAt;
    private LocalDateTime completedAt;

    // Additional fields for UI
    private boolean canBeAssigned;
    private boolean canBeStarted;
    private boolean canBeCompleted;
    private boolean canBeCancelled;
    private boolean isActive;
    private String formattedBookingDateTime;
    private List<BookingStatusHistoryResponse> statusHistory;
}
