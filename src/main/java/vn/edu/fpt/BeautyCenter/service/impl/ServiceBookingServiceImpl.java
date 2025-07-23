package vn.edu.fpt.BeautyCenter.service.impl;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Service Implementation
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.BeautyCenter.dto.request.*;
import vn.edu.fpt.BeautyCenter.dto.response.BookingResponse;
import vn.edu.fpt.BeautyCenter.dto.response.BookingStatusHistoryResponse;
import vn.edu.fpt.BeautyCenter.entity.*;
import vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus;
import vn.edu.fpt.BeautyCenter.exception.AppException;
import vn.edu.fpt.BeautyCenter.exception.ErrorCode;
import vn.edu.fpt.BeautyCenter.mapper.ServiceBookingMapper;
import vn.edu.fpt.BeautyCenter.repository.*;
import vn.edu.fpt.BeautyCenter.service.ServiceBookingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ServiceBookingServiceImpl implements ServiceBookingService {

    private final ServiceBookingRepository bookingRepository;
    private final BookingStatusHistoryRepository statusHistoryRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final ServiceBookingMapper bookingMapper;

    @Override
    public BookingResponse createBooking(BookingRequest request, String customerId) {
        log.info("Creating booking for service: {} by customer: {}", request.getServiceId(), customerId);

        // Validate service exists
        vn.edu.fpt.BeautyCenter.entity.Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new AppException(ErrorCode.SERVICE_NOT_FOUND));

        // Validate customer exists
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Validate booking time
        if (!isValidBookingTime(request.getBookingDate(), request.getBookingTime())) {
            throw new AppException(ErrorCode.INVALID_BOOKING_TIME);
        }

        // Check time slot availability
        if (!isTimeSlotAvailable(request.getServiceId(), request.getBookingDate(), request.getBookingTime())) {
            throw new AppException(ErrorCode.TIME_SLOT_NOT_AVAILABLE);
        }

        // Create booking entity
        ServiceBooking booking = bookingMapper.toEntity(request);
        booking.setService(service);
        booking.setCustomer(customer);
        booking.setStatus(BookingStatus.PENDING);
        booking.setTotalPrice(service.getPrice());

        // Save booking
        ServiceBooking savedBooking = bookingRepository.save(booking);

        // Create status history
        createStatusHistory(savedBooking, null, BookingStatus.PENDING, customerId, "Booking created");

        log.info("Booking created successfully with ID: {}", savedBooking.getBookingId());
        return bookingMapper.toResponse(savedBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookingResponse> getBookingById(String bookingId) {
        log.debug("Fetching booking with ID: {}", bookingId);

        return bookingRepository.findById(bookingId)
                .map(bookingMapper::toResponse);
    }

    @Override
    public BookingResponse assignBookingToStaff(String bookingId, BookingAssignmentRequest request, String adminId) {
        log.info("Assigning booking: {} to staff: {} by admin: {}", bookingId, request.getStaffId(), adminId);

        // Find booking
        ServiceBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        if (!booking.canBeAssigned()) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_BE_ASSIGNED);
        }

        // Validate staff
        User staff = userRepository.findById(request.getStaffId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Check for conflicts
        long conflicts = bookingRepository.countConflictingBookings(
                request.getStaffId(), booking.getBookingDate(), booking.getBookingTime());

        if (conflicts > 0) {
            throw new AppException(ErrorCode.STAFF_SCHEDULE_CONFLICT);
        }

        // Update booking
        BookingStatus oldStatus = booking.getStatus();
        booking.setStaff(staff);
        booking.setStatus(BookingStatus.ASSIGNED);

        ServiceBooking updatedBooking = bookingRepository.save(booking);

        // Create status history
        createStatusHistory(updatedBooking, oldStatus, BookingStatus.ASSIGNED,
                adminId, request.getAssignmentNotes());

        log.info("Booking assigned successfully: {} to staff: {}", bookingId, request.getStaffId());
        return bookingMapper.toResponse(updatedBooking);
    }

    @Override
    public BookingResponse startBooking(String bookingId, String staffId) {
        log.info("Starting booking: {} by staff: {}", bookingId, staffId);

        ServiceBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        // Validate staff assignment
        if (booking.getStaff() == null || !booking.getStaff().getUserId().equals(staffId)) {
            throw new AppException(ErrorCode.BOOKING_NOT_ASSIGNED_TO_STAFF);
        }

        if (!booking.canBeStarted()) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_BE_STARTED);
        }

        // Update status
        BookingStatus oldStatus = booking.getStatus();
        booking.setStatus(BookingStatus.IN_PROGRESS);

        ServiceBooking updatedBooking = bookingRepository.save(booking);

        // Create status history
        createStatusHistory(updatedBooking, oldStatus, BookingStatus.IN_PROGRESS,
                staffId, "Service started");

        log.info("Booking started successfully: {}", bookingId);
        return bookingMapper.toResponse(updatedBooking);
    }

    @Override
    public BookingResponse completeBooking(String bookingId, BookingCompletionRequest request, String staffId) {
        log.info("Completing booking: {} by staff: {}", bookingId, staffId);

        ServiceBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        // Validate staff
        if (booking.getStaff() == null || !booking.getStaff().getUserId().equals(staffId)) {
            throw new AppException(ErrorCode.BOOKING_NOT_ASSIGNED_TO_STAFF);
        }

        if (!booking.canBeCompleted()) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_BE_COMPLETED);
        }

        // Update status
        BookingStatus oldStatus = booking.getStatus();
        booking.setStatus(BookingStatus.COMPLETED);

        ServiceBooking updatedBooking = bookingRepository.save(booking);

        // Create status history
        createStatusHistory(updatedBooking, oldStatus, BookingStatus.COMPLETED,
                staffId, request.getCompletionNotes());

        log.info("Booking completed successfully: {}", bookingId);
        return bookingMapper.toResponse(updatedBooking);
    }

    @Override
    public BookingResponse cancelBooking(String bookingId, BookingCancellationRequest request, String currentUserId) {
        log.info("Cancelling booking: {} by user: {}", bookingId, currentUserId);

        ServiceBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        if (!booking.canBeCancelled()) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_BE_CANCELLED);
        }

        // Update booking
        BookingStatus oldStatus = booking.getStatus();
        booking.setStatus(BookingStatus.CANCELLED);
        booking.setCancellationReason(request.getCancellationReason());

        ServiceBooking updatedBooking = bookingRepository.save(booking);

        // Create status history
        createStatusHistory(updatedBooking, oldStatus, BookingStatus.CANCELLED,
                currentUserId, request.getCancellationReason());

        log.info("Booking cancelled successfully: {}", bookingId);
        return bookingMapper.toResponse(updatedBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getCustomerBookings(String customerId) {
        List<ServiceBooking> bookings = bookingRepository.findByCustomerUserIdOrderByCreatedAtDesc(customerId);
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getCustomerBookings(String customerId, Pageable pageable) {
        Page<ServiceBooking> bookingsPage = bookingRepository.findByCustomerUserIdOrderByCreatedAtDesc(customerId, pageable);
        return bookingsPage.map(booking -> {
            try {
                return bookingMapper.toResponse(booking);
            } catch (Exception e) {
                log.error("Mapping error for bookingId = {}", booking.getBookingId(), e);
                throw new RuntimeException("Lỗi chuyển đổi dữ liệu booking"+ e.getMessage());
            }
        });
    }
    // Method riêng cho booking detail (có statusHistory)
    @Override
    @Transactional(readOnly = true)
    public Optional<BookingResponse> getBookingByIdWithHistory(String bookingId) {
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    try {
                        // Sử dụng toDetailResponse (có statusHistory)
                        return bookingMapper.toDetailResponse(booking);
                    } catch (Exception e) {
                        log.error("Mapping error for booking detail {}: ", bookingId, e);
                        throw new RuntimeException("Lỗi chuyển đổi dữ liệu booking detail: " + e.getMessage(), e);
                    }
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getStaffBookings(String staffId) {
        List<ServiceBooking> bookings = bookingRepository.findByStaffUserIdOrderByCreatedAtDesc(staffId);
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getStaffBookings(String staffId, Pageable pageable) {
        Page<ServiceBooking> bookingsPage = bookingRepository.findByStaffUserIdOrderByCreatedAtDesc(staffId, pageable);
        return bookingsPage.map(bookingMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getStaffActiveBookings(String staffId) {
        List<ServiceBooking> bookings = bookingRepository.findActiveBookingsByStaff(staffId);
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getPendingBookings() {
        List<ServiceBooking> bookings = bookingRepository.findByStatusOrderByCreatedAtDesc(BookingStatus.PENDING);
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getAllBookings(Pageable pageable) {
        Page<ServiceBooking> bookingsPage = bookingRepository.findAll(pageable);
        return bookingsPage.map(bookingMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocalTime> getAvailableTimeSlots(String serviceId, LocalDate date) {
        // Get booked time slots
        List<LocalTime> bookedSlots = bookingRepository.findBookedTimeSlots(serviceId, date);

        // Generate all possible time slots (8:00 AM to 6:00 PM, every 30 minutes)
        List<LocalTime> allSlots = generateTimeSlots();

        // Remove booked slots
        return allSlots.stream()
                .filter(slot -> !bookedSlots.contains(slot))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isTimeSlotAvailable(String serviceId, LocalDate date, LocalTime time) {
        List<LocalTime> bookedSlots = bookingRepository.findBookedTimeSlots(serviceId, date);
        return !bookedSlots.contains(time);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isValidBookingTime(LocalDate date, LocalTime time) {
        if (date == null || time == null) return false;

        // Must be future date or today with future time
        LocalDate today = LocalDate.now();
        if (date.isBefore(today)) return false;

        if (date.equals(today)) {
            LocalTime now = LocalTime.now();
            if (time.isBefore(now.plusHours(1))) return false; // At least 1 hour advance booking
        }

        // Business hours: 8:00 AM to 6:00 PM
        LocalTime openTime = LocalTime.of(8, 0);
        LocalTime closeTime = LocalTime.of(18, 0);

        return !time.isBefore(openTime) && !time.isAfter(closeTime);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingStatusHistoryResponse> getBookingStatusHistory(String bookingId) {
        List<BookingStatusHistory> history = statusHistoryRepository.findByBookingBookingIdOrderByCreatedAtDesc(bookingId);
        return bookingMapper.toStatusHistoryResponseList(history);
    }

    // Helper methods
    private void createStatusHistory(ServiceBooking booking, BookingStatus oldStatus,
                                     BookingStatus newStatus, String changedBy, String reason) {
        User changer = userRepository.findById(changedBy)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        BookingStatusHistory history = BookingStatusHistory.builder()
                .booking(booking)
                .oldStatus(oldStatus)
                .newStatus(newStatus)
                .changedBy(changer)
                .changeReason(reason)
                .build();

        statusHistoryRepository.save(history);
    }

    private List<LocalTime> generateTimeSlots() {
        List<LocalTime> slots = Arrays.asList(
                LocalTime.of(8, 0), LocalTime.of(8, 30),
                LocalTime.of(9, 0), LocalTime.of(9, 30),
                LocalTime.of(10, 0), LocalTime.of(10, 30),
                LocalTime.of(11, 0), LocalTime.of(11, 30),
                LocalTime.of(12, 0), LocalTime.of(12, 30),
                LocalTime.of(13, 0), LocalTime.of(13, 30),
                LocalTime.of(14, 0), LocalTime.of(14, 30),
                LocalTime.of(15, 0), LocalTime.of(15, 30),
                LocalTime.of(16, 0), LocalTime.of(16, 30),
                LocalTime.of(17, 0), LocalTime.of(17, 30)
        );
        return slots;
    }

    // Additional implementation methods...
    @Override
    public BookingResponse updateBooking(String bookingId, BookingRequest request, String currentUserId) {
        // Implementation for updating booking details
        throw new UnsupportedOperationException("Update booking not implemented yet");
    }

    @Override
    public void deleteBooking(String bookingId, String currentUserId) {
        // Implementation for deleting booking
        throw new UnsupportedOperationException("Delete booking not implemented yet");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponse> getBookingsByStatus(BookingStatus status, Pageable pageable) {
        Page<ServiceBooking> bookingsPage = bookingRepository.findByStatusOrderByCreatedAtDesc(status, pageable);
        return bookingsPage.map(bookingMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingsByDate(LocalDate date) {
        List<ServiceBooking> bookings = bookingRepository.findByBookingDateOrderByBookingTimeAsc(date);
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<ServiceBooking> bookings = bookingRepository.findByBookingDateBetweenOrderByBookingDateAscBookingTimeAsc(startDate, endDate);
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public long getBookingCountByStatus(BookingStatus status) {
        return bookingRepository.countByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCustomerBookingCount(String customerId) {
        return bookingRepository.countByCustomerUserId(customerId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getStaffBookingCount(String staffId) {
        return bookingRepository.countByStaffUserId(staffId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getServiceBookingCount(String serviceId) {
        return bookingRepository.countByServiceServiceId(serviceId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getOverdueBookings() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        List<ServiceBooking> bookings = bookingRepository.findOverdueBookings(today, now);
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getTodayBookings() {
        List<ServiceBooking> bookings = bookingRepository.findByBookingDateOrderByBookingTimeAsc(LocalDate.now());
        return bookingMapper.toResponseList(bookings);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean canUserAccessBooking(String bookingId, String userId) {
        Optional<ServiceBooking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) return false;

        ServiceBooking booking = bookingOpt.get();
        return booking.getCustomer().getUserId().equals(userId) ||
                (booking.getStaff() != null && booking.getStaff().getUserId().equals(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean canUserModifyBooking(String bookingId, String userId) {
        Optional<ServiceBooking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) return false;

        ServiceBooking booking = bookingOpt.get();
        return booking.getCustomer().getUserId().equals(userId) &&
                booking.getStatus() == BookingStatus.PENDING;
    }

    @Override
    public void sendBookingNotifications(String bookingId) {
        // Implementation for sending notifications
        log.info("Sending notifications for booking: {}", bookingId);
    }

    @Override
    public void checkAndUpdateOverdueBookings() {
        // Implementation for checking and updating overdue bookings
        log.info("Checking for overdue bookings");
    }

    @Override
    public List<User> getAvailableStaffForBooking(String bookingId) {
        // 1. Fetch the booking
        ServiceBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        LocalDate date = booking.getBookingDate();
        LocalTime time = booking.getBookingTime();

        // 2. Load all active staff users
        List<User> allStaff = userRepository.findByRoleOrderByFullName("STAFF"); // or Role.STAFF enum

        // 3. Filter out staff who have a conflicting booking
        List<String> busyStaffIds = bookingRepository.findBookedStaffIds(
                date, time,
                List.of(BookingStatus.ASSIGNED, BookingStatus.IN_PROGRESS)
        );

        // 4. Map to DTO
        return allStaff.stream()
                .filter(staff -> !busyStaffIds.contains(staff.getUserId()))
                .toList();
    }
}
