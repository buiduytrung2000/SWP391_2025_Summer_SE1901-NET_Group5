package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "staff_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffSchedule {

    @Id
    @Column(name = "schedule_id", nullable = false, columnDefinition = "char(36)")
    private String scheduleId = UUID.randomUUID().toString();

    @Column(name = "staff_id", nullable = false, columnDefinition = "char(36)")
    private String staffId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Staff staff;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('UPCOMING','ATTENDANCE','ABSENT')", nullable = false)
    private Status status = Status.UPCOMING;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Status {
        UPCOMING,
        ATTENDANCE,
        ABSENT
    }

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
