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

import lombok.Getter;
import lombok.Setter;

/**
 * Custom runtime exception for application-specific error handling.
 * <p>
 * Wraps error codes from {@link ErrorCode} to provide consistent error messages
 * across the application. Used for business logic failures and validation errors.
 * </p>
 */
@Setter
@Getter
public class AppException extends RuntimeException {

    /** Error code containing the specific error type and message. */
    private ErrorCode errorCode;

    /**
     * Constructs a new application exception with the specified error code.
     *
     * @param errorCode the error code defining the exception type and message
     */
    public AppException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
