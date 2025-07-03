package vn.edu.fpt.BeautyCenter.entity.enums;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-25>      <1.0>              TrungBD      First Implement
 */

public enum BlogStatus {
    DRAFT("Draft"),
    PUBLISHED("Published"),
    ARCHIVED("Archived");

    private final String displayName;

    BlogStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
