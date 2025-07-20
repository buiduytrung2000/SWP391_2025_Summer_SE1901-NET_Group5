package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "blog_tags")
public class BlogTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "tag_name", nullable = false, length = 50)
    private String tagName;

    @ManyToMany(mappedBy = "blogTags")
    private Set<Blog> blogs = new LinkedHashSet<>();

    @Size(max = 50)
    @NotNull
    @Column(name = "tag_slug", nullable = false, length = 50)
    private String tagSlug;

    @ColumnDefault("0")
    @Column(name = "usage_count")
    private Integer usageCount;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}