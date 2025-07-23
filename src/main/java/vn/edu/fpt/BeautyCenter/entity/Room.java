package vn.edu.fpt.BeautyCenter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Room {

    @Id
    @Column(name = "room_id", columnDefinition = "CHAR(36)")
    private String roomId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20)
    private RoomType type = RoomType.OTHER;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private RoomStatus status = RoomStatus.AVAILABLE;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum RoomType {
        MASSAGE, FACIAL, TATTOO, OTHER
    }

    public enum RoomStatus {
        AVAILABLE, OCCUPIED, MAINTENANCE
    }
}
