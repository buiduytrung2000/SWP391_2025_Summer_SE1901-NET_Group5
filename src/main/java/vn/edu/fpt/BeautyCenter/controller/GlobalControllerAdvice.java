package vn.edu.fpt.BeautyCenter.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-08-03>      <1.0>              TrungBD      First Implement
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute("currentURI")
    public String currentUri(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
