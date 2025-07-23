package vn.edu.fpt.BeautyCenter.entity;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-22>      <1.0>              TrungBD      Service Booking Entity
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vn.edu.fpt.BeautyCenter.entity.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "service_bookings")
public class ServiceBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private String bookingId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false, columnDefinition = "CHAR(36)")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private User staff;

    @NotNull
    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @NotNull
    @Column(name = "booking_time", nullable = false)
    private LocalTime bookingTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PENDING'")
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Lob
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Lob
    @Column(name = "cancellation_reason", columnDefinition = "TEXT")
    private String cancellationReason;

    @Positive
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "assigned_at")
    private Instant assignedAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingStatusHistory> statusHistory = new LinkedHashSet<>();

    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();
        if (this.createdAt == null) {
            this.createdAt = now;
        }
        this.updatedAt = now;

        if (this.status == null) {
            this.status = BookingStatus.PENDING;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();

        // Set timestamps based on status changes
        if (this.status == BookingStatus.ASSIGNED && this.assignedAt == null) {
            this.assignedAt = Instant.now();
        }

        if (this.status == BookingStatus.COMPLETED && this.completedAt == null) {
            this.completedAt = Instant.now();
        }
    }

    // Business logic methods
    public boolean canBeAssigned() {
        return this.status == BookingStatus.PENDING;
    }

    public boolean canBeStarted() {
        return this.status == BookingStatus.ASSIGNED;
    }

    public boolean canBeCompleted() {
        return this.status == BookingStatus.IN_PROGRESS;
    }

    public boolean canBeCancelled() {
        return this.status == BookingStatus.PENDING ||
                this.status == BookingStatus.ASSIGNED ||
                this.status == BookingStatus.IN_PROGRESS;
    }

    public boolean isActive() {
        return this.status != BookingStatus.COMPLETED &&
                this.status != BookingStatus.CANCELLED;
    }
}
