package vn.edu.fpt.BeautyCenter.dto.response;

/*
 * Copyright(C) 2025,  FPT University.
 * SBS :
 *  Smart Beauty System
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * <2025-07-21>      <1.0>              TrungBD      First Implement
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String commentId;
    private String content;
    private String blogId;
    private String blogTitle;
    private String authorId;
    private String authorName;
    private String authorAvatar; // URL to user avatar
    private Integer likeCount;
    private Boolean isActive;
    private Boolean isLikedByCurrentUser; // Indicates if current user liked this comment
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

