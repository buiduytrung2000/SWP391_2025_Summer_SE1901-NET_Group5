package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Staff {

    @Id
    @Column(name = "user_id", nullable = false,columnDefinition = "char(36)")
    private String userId;

    @Size(max = 50)
    @NotNull
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 100)
    @Column(name = "full_name", length = 100)
    private String fullName;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "gender", columnDefinition = "varchar(10)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        Male,
        Female,
        Other;
    }

    @Size(max = 100)
    @Column(name = "position", length = 100)
    private String position;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @Size(max = 255)
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status", columnDefinition = "enum")
    @Enumerated(EnumType.STRING)
    private Status  status;
    public enum Status{
        active,
        inactive
    }



    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
