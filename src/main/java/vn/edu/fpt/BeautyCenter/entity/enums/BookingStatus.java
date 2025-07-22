package vn.edu.fpt.BeautyCenter.entity.enums;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Status Enum
 */

public enum BookingStatus {
    PENDING("Pending", "Chờ xử lý"),
    ASSIGNED("Assigned", "Đã phân công"),
    IN_PROGRESS("In Progress", "Đang thực hiện"),
    COMPLETED("Completed", "Hoàn thành"),
    CANCELLED("Cancelled", "Đã hủy");

    private final String englishName;
    private final String vietnameseName;

    BookingStatus(String englishName, String vietnameseName) {
        this.englishName = englishName;
        this.vietnameseName = vietnameseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public String getDisplayName() {
        return vietnameseName;
    }

    public boolean canTransitionTo(BookingStatus newStatus) {
        switch (this) {
            case PENDING:
                return newStatus == ASSIGNED || newStatus == CANCELLED;
            case ASSIGNED:
                return newStatus == IN_PROGRESS || newStatus == CANCELLED;
            case IN_PROGRESS:
                return newStatus == COMPLETED || newStatus == CANCELLED;
            case COMPLETED:
            case CANCELLED:
                return false;
            default:
                return false;
        }
    }
}
