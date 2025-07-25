package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import vn.edu.fpt.BeautyCenter.entity.enums.BlogStatus;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-25>      <1.0>              TrungBD      First Implement
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Sử dụng UUID strategy
    @Column(name = "blog_id", nullable = false, length = 36, columnDefinition = "CHAR(36)")
    private String blogId;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Size(max = 255)
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ColumnDefault("'Draft'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BlogStatus status;

    @ManyToMany
    @JoinTable(name = "blog_tag_map",
            joinColumns = @JoinColumn(name = "blog_id" ,  columnDefinition = "CHAR(36)"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",  columnDefinition = "INT"))
    private Set<BlogTag> blogTags = new LinkedHashSet<>();

    @Lob
    @Column(name = "excerpt", columnDefinition = "TEXT")
    private String excerpt;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "category_id", columnDefinition = "INT")
    private BlogCategory category;

    @ColumnDefault("0")
    @Column(name = "featured")
    private Boolean featured;

    @ColumnDefault("0")
    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "published_at")
    private Instant publishedAt;
    
    @PrePersist
    public void prePersist() {
        Instant now = Instant.now();

        if (this.createdAt == null) {
            this.createdAt = now;
        }

        this.updatedAt = now;

        // Chỉ set publishedAt nếu status là PUBLISHED và chưa có publishedAt
        if (this.status == BlogStatus.PUBLISHED && this.publishedAt == null) {
            this.publishedAt = now;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();

        // Nếu status chuyển thành PUBLISHED và chưa có publishedAt
        if (this.status == BlogStatus.PUBLISHED && this.publishedAt == null) {
            this.publishedAt = Instant.now();
        }

        // Nếu status không phải PUBLISHED thì xóa publishedAt
        if (this.status != BlogStatus.PUBLISHED) {
            this.publishedAt = null;
        }
    }
}
