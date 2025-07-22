package vn.edu.fpt.BeautyCenter.service;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Service Interface
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.BeautyCenter.dto.request.BookingAssignmentRequest;
import vn.edu.fpt.BeautyCenter.dto.request.BookingCancellationRequest;
import vn.edu.fpt.BeautyCenter.dto.request.BookingCompletionRequest;
import vn.edu.fpt.BeautyCenter.dto.request.BookingRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BookingResponse;
import vn.edu.fpt.BeautyCenter.dto.response.BookingStatusHistoryResponse;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ServiceBookingService {

    // CRUD operations
    BookingResponse createBooking(BookingRequest request, String customerId);
    Optional<BookingResponse> getBookingById(String bookingId);
    BookingResponse updateBooking(String bookingId, BookingRequest request, String currentUserId);
    void deleteBooking(String bookingId, String currentUserId);

    // Customer operations
    List<BookingResponse> getCustomerBookings(String customerId);
    Page<BookingResponse> getCustomerBookings(String customerId, Pageable pageable);
    Optional<BookingResponse> getBookingByIdWithHistory(String bookingId);
    // Admin operations
    Page<BookingResponse> getAllBookings(Pageable pageable);
    List<BookingResponse> getPendingBookings();
    Page<BookingResponse> getBookingsByStatus(BookingStatus status, Pageable pageable);
    BookingResponse assignBookingToStaff(String bookingId, BookingAssignmentRequest request, String adminId);

    // Staff operations
    List<BookingResponse> getStaffBookings(String staffId);
    Page<BookingResponse> getStaffBookings(String staffId, Pageable pageable);
    List<BookingResponse> getStaffActiveBookings(String staffId);
    BookingResponse startBooking(String bookingId, String staffId);
    BookingResponse completeBooking(String bookingId, BookingCompletionRequest request, String staffId);
    BookingResponse cancelBooking(String bookingId, BookingCancellationRequest request, String currentUserId);

    // Scheduling operations
    List<LocalTime> getAvailableTimeSlots(String serviceId, LocalDate date);
    boolean isTimeSlotAvailable(String serviceId, LocalDate date, LocalTime time);
    List<BookingResponse> getBookingsByDate(LocalDate date);
    List<BookingResponse> getBookingsByDateRange(LocalDate startDate, LocalDate endDate);

    // Statistics and reports
    long getBookingCountByStatus(BookingStatus status);
    long getCustomerBookingCount(String customerId);
    long getStaffBookingCount(String staffId);
    long getServiceBookingCount(String serviceId);
    List<BookingResponse> getOverdueBookings();
    List<BookingResponse> getTodayBookings();

    // Status history
    List<BookingStatusHistoryResponse> getBookingStatusHistory(String bookingId);

    // Validation methods
    boolean canUserAccessBooking(String bookingId, String userId);
    boolean canUserModifyBooking(String bookingId, String userId);
    boolean isValidBookingTime(LocalDate date, LocalTime time);

    // Business logic
    void sendBookingNotifications(String bookingId);
    void checkAndUpdateOverdueBookings();

    List<User> getAvailableStaffForBooking(String bookingId);
}
