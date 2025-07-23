package vn.edu.fpt.BeautyCenter.repository;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Booking Status History Repository
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.BeautyCenter.entity.BookingStatusHistory;

import java.util.List;

@Repository
public interface BookingStatusHistoryRepository extends JpaRepository<BookingStatusHistory, String> {

    // Find history by booking ID
    List<BookingStatusHistory> findByBookingBookingIdOrderByCreatedAtDesc(String bookingId);

    // Find history by user who made changes
    List<BookingStatusHistory> findByChangedByUserIdOrderByCreatedAtDesc(String userId);

    // Find recent status changes
    List<BookingStatusHistory> findTop10ByOrderByCreatedAtDesc();
}
