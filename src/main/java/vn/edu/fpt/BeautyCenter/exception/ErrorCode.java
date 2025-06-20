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
    /** Unclassified or unexpected exception. */
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),

    /** Attempted to create a user that already exists. */
    USER_EXISTED(1001, "User already exists"),

    /** Invalid authentication or encryption key provided. */
    INVALID_KEY(1002, "Invalid message key"),

    /** Username does not meet minimum length requirement. */
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),

    /** Password does not meet complexity requirements. */
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),

    /** Attempted to create a duplicate service. */
    SERVICE_EXISTED(3000, "Service already exists"),

    /** Requested service could not be found. */
    SERVICE_NOT_FOUND(3001, "Service not found"),
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

    /** Numerical code for machine-readable error identification. */
    private int errorCode;

    /** Descriptive message for client-facing error reporting. */
    private String errorMessage;
}
