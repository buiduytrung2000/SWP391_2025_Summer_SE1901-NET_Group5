package vn.edu.fpt.BeautyCenter.mapper;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Mapper
 */

import org.mapstruct.*;
import vn.edu.fpt.BeautyCenter.dto.request.BookingRequest;
import vn.edu.fpt.BeautyCenter.dto.response.BookingResponse;
import vn.edu.fpt.BeautyCenter.dto.response.BookingStatusHistoryResponse;
import vn.edu.fpt.BeautyCenter.entity.ServiceBooking;
import vn.edu.fpt.BeautyCenter.entity.BookingStatusHistory;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {TimeMapper.class, UserMapper.class, ServiceMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ServiceBookingMapper {

    @Mapping(target = "bookingId", ignore = true)
    @Mapping(target = "service", ignore = true) // Set manually in service
    @Mapping(target = "customer", ignore = true) // Set manually in service
    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "status", ignore = true) // Set by @PrePersist
    @Mapping(target = "totalPrice", ignore = true) // Set manually in service
    @Mapping(target = "createdAt", ignore = true) // Set by @PrePersist
    @Mapping(target = "updatedAt", ignore = true) // Set by @PrePersist
    @Mapping(target = "assignedAt", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "cancellationReason", ignore = true)
    @Mapping(target = "statusHistory", ignore = true)
    ServiceBooking toEntity(BookingRequest request);

    @Named("standardResponse")
    @Mapping(target = "serviceId", source = "service.serviceId")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "serviceDescription", source = "service.content")
    @Mapping(target = "servicePrice", source = "service.price")
    @Mapping(target = "serviceDuration", source = "service.duration")
    @Mapping(target = "customerId", source = "customer.userId")
    @Mapping(target = "customerName", source = "customer.fullName")
    @Mapping(target = "customerEmail", source = "customer.email")
    @Mapping(target = "customerPhone", source = "customer.phone")
    @Mapping(target = "staffId", source = "staff.userId")
    @Mapping(target = "staffName", source = "staff.fullName")
    @Mapping(target = "staffEmail", source = "staff.email")
    @Mapping(target = "statusDisplayName", source = "status", qualifiedByName = "mapStatusDisplayName")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "assignedAt", source = "assignedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "completedAt", source = "completedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "canBeAssigned", expression = "java(booking.canBeAssigned())")
    @Mapping(target = "canBeStarted", expression = "java(booking.canBeStarted())")
    @Mapping(target = "canBeCompleted", expression = "java(booking.canBeCompleted())")
    @Mapping(target = "canBeCancelled", expression = "java(booking.canBeCancelled())")
    @Mapping(target = "active", expression = "java(booking.isActive())")
    @Mapping(target = "formattedBookingDateTime",
            expression = "java(formatBookingDateTime(booking.getBookingDate(), booking.getBookingTime()))")
    @Mapping(target = "statusHistory", ignore = true) // ← IGNORE để tránh lazy loading
    BookingResponse toResponse(ServiceBooking booking);

    @Named("detailResponse")
    @Mapping(target = "serviceId", source = "service.serviceId")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "serviceDescription", source = "service.content")
    @Mapping(target = "servicePrice", source = "service.price")
    @Mapping(target = "serviceDuration", source = "service.duration")
    @Mapping(target = "customerId", source = "customer.userId")
    @Mapping(target = "customerName", source = "customer.fullName")
    @Mapping(target = "customerEmail", source = "customer.email")
    @Mapping(target = "customerPhone", source = "customer.phone")
    @Mapping(target = "staffId", source = "staff.userId")
    @Mapping(target = "staffName", source = "staff.fullName")
    @Mapping(target = "staffEmail", source = "staff.email")
    @Mapping(target = "statusDisplayName", source = "status", qualifiedByName = "mapStatusDisplayName")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "assignedAt", source = "assignedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "completedAt", source = "completedAt", qualifiedByName = "instantToLocalDateTime")
    @Mapping(target = "canBeAssigned", expression = "java(booking.canBeAssigned())")
    @Mapping(target = "canBeStarted", expression = "java(booking.canBeStarted())")
    @Mapping(target = "canBeCompleted", expression = "java(booking.canBeCompleted())")
    @Mapping(target = "canBeCancelled", expression = "java(booking.canBeCancelled())")
    @Mapping(target = "active", expression = "java(booking.isActive())")
    @Mapping(target = "formattedBookingDateTime",
            expression = "java(formatBookingDateTime(booking.getBookingDate(), booking.getBookingTime()))")
    @Mapping(target = "statusHistory", source = "statusHistory") // Giữ lại cho detail view
    BookingResponse toDetailResponse(ServiceBooking booking);


    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "staff", ignore = true)
    void updateEntity(@MappingTarget ServiceBooking booking, BookingRequest request);

    @IterableMapping(qualifiedByName = "standardResponse")
    List<BookingResponse> toResponseList(List<ServiceBooking> bookings);

    // Status History Mapping
    @Mapping(target = "bookingId", source = "booking.bookingId")
    @Mapping(target = "oldStatusDisplayName", source = "oldStatus", qualifiedByName = "mapStatusDisplayName")
    @Mapping(target = "newStatusDisplayName", source = "newStatus", qualifiedByName = "mapStatusDisplayName")
    @Mapping(target = "changedByName", source = "changedBy.fullName")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "instantToLocalDateTime")
    BookingStatusHistoryResponse toStatusHistoryResponse(BookingStatusHistory history);

    List<BookingStatusHistoryResponse> toStatusHistoryResponseList(List<BookingStatusHistory> histories);

    // Custom mapping methods
    @Named("mapStatusDisplayName")
    default String mapStatusDisplayName(vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus status) {
        return status != null ? status.getDisplayName() : null;
    }

    default String formatBookingDateTime(java.time.LocalDate date, java.time.LocalTime time) {
        if (date == null || time == null) return null;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return date.format(dateFormatter) + " lúc " + time.format(timeFormatter);
    }
}
