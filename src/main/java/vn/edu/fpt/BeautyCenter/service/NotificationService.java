package vn.edu.fpt.BeautyCenter.service;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Service for managing notification messages in the application.
 * <p>
 * Provides methods to add success, error, warning, and info messages
 * that will be displayed to users after redirects.
 * </p>
 */
@Service
public class NotificationService {

    /** Flash attribute key for success messages */
    public static final String SUCCESS_MESSAGE = "successMessage";

    /** Flash attribute key for error messages */
    public static final String ERROR_MESSAGE = "errorMessage";

    /** Flash attribute key for warning messages */
    public static final String WARNING_MESSAGE = "warningMessage";

    /** Flash attribute key for info messages */
    public static final String INFO_MESSAGE = "infoMessage";

    /**
     * Adds a success message to be displayed after redirect.
     *
     * @param redirectAttributes the redirect attributes
     * @param message the success message
     */
    public void addSuccessMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, message);
    }

    /**
     * Adds an error message to be displayed after redirect.
     *
     * @param redirectAttributes the redirect attributes
     * @param message the error message
     */
    public void addErrorMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE, message);
    }

    /**
     * Adds a warning message to be displayed after redirect.
     *
     * @param redirectAttributes the redirect attributes
     * @param message the warning message
     */
    public void addWarningMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(WARNING_MESSAGE, message);
    }

    /**
     * Adds an info message to be displayed after redirect.
     *
     * @param redirectAttributes the redirect attributes
     * @param message the info message
     */
    public void addInfoMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute(INFO_MESSAGE, message);
    }
}