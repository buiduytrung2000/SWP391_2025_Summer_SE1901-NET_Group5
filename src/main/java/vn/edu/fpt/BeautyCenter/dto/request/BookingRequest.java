package vn.edu.fpt.BeautyCenter.dto.request;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Request DTO
 */

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    @NotBlank(message = "Service ID is required")
    private String serviceId;

    @NotNull(message = "Booking date is required")
    @FutureOrPresent(message = "Booking date must be today or in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate bookingDate;

    @NotNull(message = "Booking time is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime bookingTime;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;

    // Validation methods
    public boolean isValidBookingTime() {
        if (bookingTime == null) return false;

        // Business hours: 8:00 AM to 6:00 PM
        LocalTime openTime = LocalTime.of(8, 0);
        LocalTime closeTime = LocalTime.of(18, 0);

        return !bookingTime.isBefore(openTime) && !bookingTime.isAfter(closeTime);
    }

    public boolean isValidBookingDate() {
        if (bookingDate == null) return false;

        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusMonths(3); // Max 3 months advance booking

        return !bookingDate.isBefore(today) && !bookingDate.isAfter(maxDate);
    }
}
