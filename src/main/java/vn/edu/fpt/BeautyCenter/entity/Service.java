package vn.edu.fpt.BeautyCenter.entity;

/*
 * Copyright(C) 2025, FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE         Version  AUTHOR   DESCRIPTION
 * 2025-06-08   1.0      TrungBD  First Implement
 */

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity class representing a beauty service in the system.
 * <p>
 * Maps to the 'services' table and contains service details including pricing,
 * duration, and relationships with tags. Includes audit fields for creation
 * and modification tracking.
 * </p>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "services")
public class Service {

    /** Unique service identifier generated as UUID (CHAR(36)). */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "service_id", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private String serviceId;

    /** Service name. Maximum 100 characters. */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /** Detailed service description stored as LONGTEXT. */
    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    /** Service duration in minutes (1-480). */
    @Column(name = "duration", nullable = false)
    private Integer duration;

    /** Service price with 2 decimal precision. Range: 0.01-9999.99. */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /** ID of the user who created this service. */
    @Column(name = "created_by", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private String createdBy;

    /** Timestamp of service creation (auto-generated). */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    /** Timestamp of last modification (auto-updated). */
    @ColumnDefault("CURRENT_TIMESTAMP")
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    // Relationships (commented out for potential future use)
    // @OneToMany(mappedBy = "service") private Set<Appointment> appointments = new LinkedHashSet<>();
    // @OneToMany(mappedBy = "service") private Set<Feedback> feedbacks = new LinkedHashSet<>();
    // @ManyToMany @JoinTable(...) private Set<User> users = new LinkedHashSet<>();

    /** Associated tags through join table service_tag_map. */
    @ManyToMany
    @JoinTable(
            name = "service_tag_map",
            joinColumns = @JoinColumn(
                    name = "service_id",
                    columnDefinition = "CHAR(36)"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    columnDefinition = "INT"
            )
    )
    private Set<ServiceTag> serviceTags = new LinkedHashSet<>();

    /**
     * Adds a tag to this service and maintains bidirectional relationship.
     *
     * @param tag the ServiceTag to associate
     */
    public void addTag(ServiceTag tag) {
        this.serviceTags.add(tag);
        tag.getServices().add(this);
    }

    /**
     * Removes a tag from this service and updates bidirectional relationship.
     *
     * @param tag the ServiceTag to disassociate
     */
    public void removeTag(ServiceTag tag) {
        this.serviceTags.remove(tag);
        tag.getServices().remove(this);
    }
}
