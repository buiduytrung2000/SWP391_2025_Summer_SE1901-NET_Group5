package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tokenId;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expiration", nullable = false)
    private LocalDateTime expiration;

    private boolean used;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
