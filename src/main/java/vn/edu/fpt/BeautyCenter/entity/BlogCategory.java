package vn.edu.fpt.BeautyCenter.entity;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-06-30>      <1.0>              TrungBD      First Implement
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "blog_categories")
public class BlogCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, columnDefinition = "INT")
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "category_name", nullable = false, length = 50)
    private String categoryName;

    @Size(max = 100)
    @NotNull
    @Column(name = "category_slug", nullable = false, length = 100)
    private String categorySlug;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Size(max = 7)
    @ColumnDefault("'#EA9999'")
    @Column(name = "color_code", length = 7)
    private String colorCode;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "category")
    private Set<Blog> blogs = new LinkedHashSet<>();

}
