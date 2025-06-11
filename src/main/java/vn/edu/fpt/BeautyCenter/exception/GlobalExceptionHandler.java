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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.edu.fpt.BeautyCenter.dto.request.ApiResponse;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Global exception handler for the Beauty Center application.
 * <p>
 * Provides centralized exception handling across all controllers, converting
 * various exception types into standardized API responses or appropriate redirects.
 * </p>
 */
@SuppressWarnings("rawtypes")
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles general runtime exceptions.
     * <p>
     * Returns an internal server error response with the exception message.
     * </p>
     *
     * @param ex the runtime exception that occurred
     * @return ResponseEntity with error details and 500 status
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException ex) {
        // Build error response with internal server error status
        ApiResponse response = ApiResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Handles application-specific exceptions.
     * <p>
     * Extracts error code and message from AppException and returns a bad request response.
     * </p>
     *
     * @param exception the application exception containing error code
     * @return ResponseEntity with error details and 400 status
     */
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        // Extract error code from the custom exception
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        // Set response fields from error code
        apiResponse.setCode(errorCode.getErrorCode());
        apiResponse.setMessage(errorCode.getErrorMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    /**
     * Handles validation exceptions from request body validation.
     * <p>
     * Attempts to map validation messages to error codes, falling back to INVALID_KEY
     * if no matching error code is found.
     * </p>
     *
     * @param exception the validation exception containing field errors
     * @return ResponseEntity with validation error details and 400 status
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // Extract validation error message from field error
        String enumKey = Objects.requireNonNull(exception.getFieldError()).getDefaultMessage();

        // Default to INVALID_KEY if no specific error code matches
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored) {
            // Keep default error code if enum lookup fails
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getErrorCode());
        apiResponse.setMessage(enumKey); // Use original validation message

        return ResponseEntity.badRequest().body(apiResponse);
    }

    /**
     * Handles NoSuchElementException for missing entities.
     * <p>
     * Logs error details and redirects to the admin services page.
     * Primarily used for service-related not found scenarios.
     * </p>
     *
     * @param exception the no such element exception
     * @return redirect string to admin services page
     */
    @ExceptionHandler(value = NoSuchElementException.class)
    String handlingNoSuchElementException(NoSuchElementException exception) {
        // Extract exception message
        String enumKey = Objects.requireNonNull(exception.getMessage());

        // Default to SERVICE_NOT_FOUND for this exception type
        ErrorCode errorCode = ErrorCode.SERVICE_NOT_FOUND;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored) {
            // Keep default error code if enum lookup fails
        }

        // Build response for logging purposes
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getErrorCode());
        apiResponse.setMessage(errorCode.getErrorMessage());

        // Log error details for debugging
        System.out.println("Error Code: " + apiResponse.getCode());
        System.out.println("Error Msg: " + apiResponse.getMessage());

        // Redirect to admin services page
        return "redirect:/admin/services";
    }
}
