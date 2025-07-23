package vn.edu.fpt.BeautyCenter.repository;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Repository
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.ServiceBooking;
import vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, String>,
        JpaSpecificationExecutor<ServiceBooking> {

    // Find by status
    List<ServiceBooking> findByStatusOrderByCreatedAtDesc(BookingStatus status);
    Page<ServiceBooking> findByStatusOrderByCreatedAtDesc(BookingStatus status, Pageable pageable);

    // Find by customer
    List<ServiceBooking> findByCustomerUserIdOrderByCreatedAtDesc(String customerId);
    Page<ServiceBooking> findByCustomerUserIdOrderByCreatedAtDesc(String customerId, Pageable pageable);

    // Find by staff
    List<ServiceBooking> findByStaffUserIdOrderByCreatedAtDesc(String staffId);
    Page<ServiceBooking> findByStaffUserIdOrderByCreatedAtDesc(String staffId, Pageable pageable);

    // Find by service
    List<ServiceBooking> findByServiceServiceIdOrderByCreatedAtDesc(String serviceId);
    Page<ServiceBooking> findByServiceServiceIdOrderByCreatedAtDesc(String serviceId, Pageable pageable);

    // Find by date range
    List<ServiceBooking> findByBookingDateBetweenOrderByBookingDateAscBookingTimeAsc(
            LocalDate startDate, LocalDate endDate);

    Page<ServiceBooking> findByBookingDateBetweenOrderByBookingDateAscBookingTimeAsc(
            LocalDate startDate, LocalDate endDate, Pageable pageable);

    // Find pending bookings for admin
    List<ServiceBooking> findByStatusInOrderByCreatedAtAsc(List<BookingStatus> statuses);
    Page<ServiceBooking> findByStatusInOrderByCreatedAtAsc(List<BookingStatus> statuses, Pageable pageable);

    // Find staff's active bookings
    @Query("SELECT sb FROM ServiceBooking sb WHERE sb.staff.userId = :staffId " +
            "AND sb.status IN (vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.ASSIGNED, vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.IN_PROGRESS) ORDER BY sb.bookingDate ASC, sb.bookingTime ASC")
    List<ServiceBooking> findActiveBookingsByStaff(@Param("staffId") String staffId);

    // Check booking conflicts
    @Query("SELECT COUNT(sb) FROM ServiceBooking sb WHERE sb.staff.userId = :staffId " +
            "AND sb.bookingDate = :date AND sb.bookingTime = :time " +
            "AND sb.status IN (vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.ASSIGNED, vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.IN_PROGRESS)")
    long countConflictingBookings(@Param("staffId") String staffId,
                                  @Param("date") LocalDate date,
                                  @Param("time") java.time.LocalTime time);

    // Statistics queries
    long countByStatus(BookingStatus status);
    long countByCustomerUserId(String customerId);
    long countByStaffUserId(String staffId);
    long countByServiceServiceId(String serviceId);

    // Count bookings by date range
    @Query("SELECT COUNT(sb) FROM ServiceBooking sb WHERE sb.bookingDate BETWEEN :startDate AND :endDate")
    long countByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Find today's bookings
    @Query("""
        SELECT b
        FROM ServiceBooking b
        WHERE b.bookingDate = :bookingDate
        AND b.status NOT IN (vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.COMPLETED, vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.CANCELLED)
        ORDER BY b.bookingTime ASC
    """)
    List<ServiceBooking> findByBookingDateOrderByBookingTimeAsc(LocalDate bookingDate);

    // Find overdue bookings (past date/time but not completed)
    @Query("SELECT sb FROM ServiceBooking sb WHERE " +
            "(sb.bookingDate < :currentDate OR " +
            "(sb.bookingDate = :currentDate AND sb.bookingTime < :currentTime)) " +
            "AND sb.status IN (vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.ASSIGNED, vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.IN_PROGRESS)")
    List<ServiceBooking> findOverdueBookings(@Param("currentDate") LocalDate currentDate,
                                             @Param("currentTime") java.time.LocalTime currentTime);

    // Advanced search
    @Query("SELECT sb FROM ServiceBooking sb WHERE " +
            "(:customerId IS NULL OR sb.customer.userId = :customerId) AND " +
            "(:staffId IS NULL OR sb.staff.userId = :staffId) AND " +
            "(:serviceId IS NULL OR sb.service.serviceId = :serviceId) AND " +
            "(:status IS NULL OR sb.status = :status) AND " +
            "(:startDate IS NULL OR sb.bookingDate >= :startDate) AND " +
            "(:endDate IS NULL OR sb.bookingDate <= :endDate) " +
            "ORDER BY sb.createdAt DESC")
    Page<ServiceBooking> findWithFilters(@Param("customerId") String customerId,
                                         @Param("staffId") String staffId,
                                         @Param("serviceId") String serviceId,
                                         @Param("status") BookingStatus status,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate,
                                         Pageable pageable);

    // Find available time slots for a service on a date
    @Query("SELECT sb.bookingTime FROM ServiceBooking sb WHERE sb.service.serviceId = :serviceId " +
            "AND sb.bookingDate = :date AND sb.status IN (vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.ASSIGNED, vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus.IN_PROGRESS)")
    List<java.time.LocalTime> findBookedTimeSlots(@Param("serviceId") String serviceId,
                                                  @Param("date") LocalDate date);

    @Query("""
        SELECT sb.staff.userId
        FROM ServiceBooking sb
        WHERE sb.bookingDate = :date
          AND sb.bookingTime = :time
          AND sb.staff IS NOT NULL
          AND sb.status IN :busyStatuses
    """)
    List<String> findBookedStaffIds(
            @Param("date") LocalDate date,
            @Param("time") LocalTime time,
            @Param("busyStatuses") List<BookingStatus> busyStatuses
    );
}
