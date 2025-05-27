package vn.edu.fpt.BeautyCenter.entity;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "service_id", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private String serviceId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "content", nullable = false,columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    //    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "created_by", nullable = false)
    @Column(name = "created_by", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private String createdBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    //    @OneToMany(mappedBy = "service")
//    private Set<Appointment> appointments = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "service")
//    private Set<Feedback> feedbacks = new LinkedHashSet<>();
//
//    @ManyToMany
//    @JoinTable(name = "service_staff",
//            joinColumns = @JoinColumn(name = "service_id"),
//            inverseJoinColumns = @JoinColumn(name = "staff_id"))
//    private Set<User> users = new LinkedHashSet<>();
//
    @ManyToMany
    @JoinTable(
            name = "service_tag_map",
            joinColumns = @JoinColumn(
                    name = "service_id",
                    columnDefinition = "CHAR(36)" // Chỉ định kiểu CHAR(36) cho service_id
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    columnDefinition = "INT" // Chỉ định kiểu INT cho tag_id
            )
    )
    private Set<ServiceTag> serviceTags = new LinkedHashSet<>();

    // Helper methods để quản lý tags
    public void addTag(ServiceTag tag) {
        this.serviceTags.add(tag);
        tag.getServices().add(this);
    }

    public void removeTag(ServiceTag tag) {
        this.serviceTags.remove(tag);
        tag.getServices().remove(this);
    }

}
