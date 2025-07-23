package vn.edu.fpt.BeautyCenter.exception;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * Enumeration of application error codes and corresponding messages.
 * <p>
 * Provides consistent error identification and messaging throughout the application.
 * Used in conjunction with {@link AppException} for standardized error handling.
 * </p>
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    /**
     * Unclassified or unexpected exception.
     */
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),

    /**
     * Attempted to create a user that already exists.
     */
    USER_EXISTED(1009, "User already exists"),
    USER_NOT_FOUND(1004, "User not found"),
    /**
     * Invalid authentication or encryption key provided.
     */
    INVALID_KEY(1002, "Invalid message key"),

    /**
     * Username does not meet minimum length requirement.
     */
    USERNAME_INVALID(1002, "Username must be at least 3 characters"),

    /**
     * Password does not meet complexity requirements.
     */
    PASSWORD_INVALID(1002, "Password must be at least 8 characters"),

    /**
     * Attempted to create a duplicate service.
     */
    SERVICE_EXISTED(3009, "Service already exists"),

    /**
     * Requested service could not be found.
     */
    SERVICE_NOT_FOUND(3004, "Service not found"),
    BOOKING_NOT_FOUND(3004, "Booking not found"),
    INVALID_BOOKING_TIME(3002, "Invalid booking time"),
    TIME_SLOT_NOT_AVAILABLE(3002, "Time slot is not available"),
    BOOKING_CANNOT_BE_ASSIGNED(3002, "Booking cannot be assigned"),
    BOOKING_CANNOT_BE_STARTED(3002, "Booking cannot be started"),
    BOOKING_CANNOT_BE_COMPLETED(3002, "Booking cannot be completed"),
    BOOKING_CANNOT_BE_CANCELLED(3002, "Booking cannot be cancelled"),
    BOOKING_NOT_ASSIGNED_TO_STAFF(3002, "Booking is not assigned to this staff"),
    STAFF_SCHEDULE_CONFLICT(3002, "Staff has schedule conflict at this time"),
    // Thêm vào file ErrorCode.java hiện có
    BLOG_NOT_FOUND(1404, "Blog not found"),
    BLOG_TITLE_EXISTED(1409, "Blog title already exists"),
    CATEGORY_NOT_FOUND(1404, "Category not found"),
    CATEGORY_EXISTED(1409, "Category already exists"),
    COMMENT_NOT_FOUND(1404, "Comment not found"),
    COMMENT_ACCESS_DENIED(1404, "Comment access denied"),
    TAG_NOT_FOUND(1404, "Tag not found"),
    STAFF_NOT_FOUND(2001, "Staff not found"),
    STAFF_EMAIL_EXISTED(2002, "Email already exists");


    /**
     * Constructs an error code definition.
     *
     * @param errorCode    numerical identifier for the error
     * @param errorMessage human-readable description of the error
     */
    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Numerical code for machine-readable error identification.
     */
    private int errorCode;

    /**
     * Descriptive message for client-facing error reporting.
     */
    private String errorMessage;
}
